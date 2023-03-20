package com.example.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes.Models.Note
import com.example.notes.databinding.ActivityAddNoteBinding
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {
    private  lateinit var binding: ActivityAddNoteBinding
    private lateinit var note: Note
    private lateinit var old_note:Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_note)

        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.editTitle.setText(old_note.title)
            binding.editText.setText(old_note.note)
            isUpdate = true
        } catch (e:Exception){
            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener{
            val title = binding.editTitle.setText(old_note.title)
            val note_desc = binding.editText.setText(old_note.note)

            if(title.isNotEmpty() || note_desc.isNotEmpty()){
                val formater = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                if(isUpdate){
                    note = Note(
                        old_note.id, title.toString(), note_desc.toString(),formater.format(Date()
                        ))
                }
                else{
                    note = Note(
                        old_note.id, title.toString(), note_desc.toString(),formater.format(Date()
                        ))
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            else{
                Toast.makeText(this@AddNote,"s'il-vous-plait, entrez quelque chose",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }

        binding.imgBack.setOnClickListener{
            onBackPressed()
        }

    }
}


