package com.example.notes.Database

import androidx.lifecycle.LiveData
import com.example.notes.Models.Note

class NoteRepository(private val noteDao: NoteDao) {

    //cette variable va contenir la liste des notes present dans la bd
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    //ajout d'une note '
    suspend fun insertNote(note:Note){
        noteDao.insert(note)
    }

    // supression d'une note
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    // edition d'une note
    suspend fun updateNote(note: Note){
        noteDao.update(note.id, note.title, note.note)
    }
}