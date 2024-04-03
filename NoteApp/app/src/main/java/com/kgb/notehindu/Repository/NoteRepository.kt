package com.kgb.notehindu.Repository

import androidx.lifecycle.LiveData
import com.kgb.notehindu.Dao.NotesDao
import com.kgb.notehindu.Model.Notes

class NoteRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> = dao.getNotes()

    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()

    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()


    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }


}