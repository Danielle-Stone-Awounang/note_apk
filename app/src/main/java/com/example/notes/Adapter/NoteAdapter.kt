package com.example.notes.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Models.Note
import com.example.notes.R

class NoteAdapter(private val context: Context):RecyclerView.Adapter<NoteAdapter.noteViewHolder> {

    private val notesList:ArrayList<Note>()
    private val fullList:ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    // recuperation du nombre de notes
    override fun getItemCount(): Int {
        return notesList.size
    }

    // recuperation des elements de la liste
    inner class noteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val notes_layout=itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note_tv = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }
}