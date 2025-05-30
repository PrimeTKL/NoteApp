package com.example.notessqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notessqlite.databinding.ActivityUpdateNoteBinding


class UpdateNoteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)

        db = NoteDatabaseHelper(this)

        setContentView(binding.root)
        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()

            val updateNote = Note(noteId, newTitle, newContent)

            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Save Change", Toast.LENGTH_SHORT).show()

        }
    }
}