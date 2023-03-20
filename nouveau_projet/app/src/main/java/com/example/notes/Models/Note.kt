package com.example.notes.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//grace a cette classe, nous creons l'object note qui sera juste utilise comme donnee

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "title") val title:String?,
    @ColumnInfo(name = "note") val note:String?,
    @ColumnInfo(name="date") val date:String?
):Serializable
