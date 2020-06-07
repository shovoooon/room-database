package com.shovon.roomdatabase.database

import androidx.room.*

/**
 * Created by Itz Shovon on 4/16/2020
 */
@Dao //Dao interface is for all room functions in interface like insert
interface NoteDao {

    //insert data in room
    @Insert
    fun insertNote(note: Note)

    //insert multiple data in room
    @Insert
    fun addMultipleNote(vararg note: Note)

    //query
    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getAllNote() : List<Note>

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}
