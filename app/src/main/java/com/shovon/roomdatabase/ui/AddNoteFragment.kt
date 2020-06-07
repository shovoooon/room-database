package com.shovon.roomdatabase.ui


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.shovon.roomdatabase.R
import com.shovon.roomdatabase.database.Note
import com.shovon.roomdatabase.database.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : Fragment() {

    private var mNote:Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            mNote = AddNoteFragmentArgs.fromBundle(it).note
            etNoteTitle.setText(mNote?.title)
            etNote.setText(mNote?.note)
        }

        btnSave.setOnClickListener {
            val title = etNoteTitle.text.toString().trim()
            val noteBody = etNote.text.toString().trim()

            if (title.isEmpty() || noteBody.isEmpty()){
                return@setOnClickListener
            }
            val note = Note(title, noteBody)

            saveNote(note)
        }
    }

    private fun saveNote(note: Note){
        class SaveNote : AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {

                if (mNote == null){
                    NoteDatabase(activity!!).getNoteDao().insertNote(note)
                }else{
                    note.id = mNote!!.id
                    NoteDatabase(activity!!).getNoteDao().updateNote(note)
                }
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                if (mNote == null) {
                    activity!!.toast("Note Saved")
                }else{
                    activity!!.toast("Note Updated")
                }

                val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
                Navigation.findNavController(view!!).navigate(action)
            }

        }
        SaveNote().execute()
    }

}
