package com.shovon.roomdatabase.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Itz Shovon on 4/16/2020
 */
@Entity
data class Note(
    val title: String,
    val note: String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
