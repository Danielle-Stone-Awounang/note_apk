package com.example.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout.VERTICAL
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.Adapter.NoteAdapter
import com.example.notes.Database.NoteDatabase
import com.example.notes.Models.Note
import com.example.notes.Models.NoteViewModel
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var biding:ActivityMainBinding
    private lateinit var  database:NoteDatabase
    lateinit var viewModel: NoteViewModel
    lateinit var  adapter:NoteAdapter
    lateinit var selectedNoted: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        //initialisatio du UI
        UnitUI()

        viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this){ list->
            list?.let{
                adapter.updateList(list)
            }
        }

        database = NoteDatabase.getDataBase(this)

    }

    private fun UnitUI() {
        biding.recyclerView.setHasFixedSize(true)
        biding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,LinearLayout.VERTICAL )
        adapter = NoteAdapter(this,this)
        biding.recyclerView.adapter=adapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode== Activity.RESULT_OK){
                val note = result.data?.getSerializableExtra("note") as? Note

                if(note!=null){
                    viewModel.insertNote(note)
                }
            }
        }

        biding.addNote.setOnClickListener{
            val intent = Intent(this, AddNote::class.java)
            getContent.launch(intent)
        }

        biding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                TODO("Not yet implemented")
            }
        }

    }
}