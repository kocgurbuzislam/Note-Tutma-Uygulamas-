package com.kgb.notehindu.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kgb.notehindu.Database.NotesDatabase
import com.kgb.notehindu.Model.Notes
import com.kgb.notehindu.Repository.NoteRepository

class NotesViewModel  (application: Application) : AndroidViewModel(application) {

    val repository: NoteRepository

    init {

        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NoteRepository(dao)

    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()

    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id: Int)
    {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes)
    {
        repository.updateNotes(notes)
    }
}