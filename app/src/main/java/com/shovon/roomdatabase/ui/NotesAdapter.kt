package com.shovon.roomdatabase.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.shovon.roomdatabase.R
import com.shovon.roomdatabase.database.Note
import kotlinx.android.synthetic.main.item.view.*

/**
 * Created by Itz Shovon on 4/16/2020
 */
class NotesAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.view.itemTitle.text = notes[position].title
        holder.view.itemNote.text = notes[position].note

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            action.note = notes[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
