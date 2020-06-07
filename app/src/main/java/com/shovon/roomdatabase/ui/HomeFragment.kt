package com.shovon.roomdatabase.ui


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.shovon.roomdatabase.R
import com.shovon.roomdatabase.database.Note
import com.shovon.roomdatabase.database.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        noteRecyclerView.hasFixedSize()
        noteRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        loadNotes()

        btnAddNote.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun loadNotes() {
        class GetAllNotes : AsyncTask<Any, Any, List<Note>>(){
            override fun doInBackground(vararg params: Any?): List<Note>? {
                return NoteDatabase(activity!!).getNoteDao().getAllNote()
            }

            override fun onPostExecute(result: List<Note>?) {
                super.onPostExecute(result)
                noteRecyclerView.adapter = NotesAdapter(result!!)
            }
        }
        GetAllNotes().execute()
    }


}
