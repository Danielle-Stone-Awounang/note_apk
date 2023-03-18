package com.example.notes.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.Models.Note


//cette interface va nous peremettre d'interargir avec la base de donnee

@Dao
interface NoteDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("SELECT * FROM notes_table ORDER BY id desc")
    fun getAllNotes():LiveData<List<Note>>

    @Query("UPDATE notes_table SET title=:title note=:note WHERE id=:id")
    suspend fun update(id:Int?, title:String?, note:String?)


}