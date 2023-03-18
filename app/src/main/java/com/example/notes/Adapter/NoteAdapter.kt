package com.example.notes.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Models.Note
import com.example.notes.R
import kotlin.random.Random

class NoteAdapter(private val context: Context, private val listener: NotesItemClickListener):RecyclerView.Adapter<NoteAdapter.noteViewHolder>() {

    private val notesList=ArrayList<Note>()
    private val fullList=ArrayList<Note>()


            //lors de la creation du viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
        return noteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent,false)
        )
    }

    // lors de l'appel du viewholder
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {
        val currentNote= notesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected= true

        holder.note_tv.text= currentNote.note
        holder.date.text=currentNote.date
        holder.date.isSelected=true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener {
            listener.onLongitemClicked(notesList[holder.adapterPosition].holder.notes_layout)
            true
        }
    }

    fun updateList(newList:List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun fillterList(search:String){
        notesList.clear()

        for(item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase())==true||
                item.note?.lowercase()?.contains(search.lowercase())==true){
                notesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    // recuperation du nombre de notes
    override fun getItemCount(): Int {
        return notesList.size
    }

    fun randomColor():Int{

        val list = ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt()

        return  list[randomIndex]
    }

    // recuperation des elements de la liste
    inner class noteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val notes_layout=itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note_tv = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

    interface NotesItemClickListener{

        fun onItemClicked(note: Note)
        fun onLongitemClicked(note: Note, cardView: CardView)
    }
}