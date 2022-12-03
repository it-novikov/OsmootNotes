package com.itnovikov.osmootnotes.data.repository

import androidx.lifecycle.LiveData
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.data.local.room.NotesDao
import com.itnovikov.osmootnotes.data.local.room.model.Tag
import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val notesDao : NotesDao) : NotesRepository {

    override fun getNotes(): LiveData<List<Note>> = notesDao.getNotes()

    override suspend fun deleteNote(id: Int) {
        notesDao.deleteNote(id)
    }

    override suspend fun addNote(note: Note) {
        notesDao.addNote(note)
    }

    override fun getTags(): LiveData<List<Tag>> = notesDao.getTags()

    override suspend fun addTag(tag: Tag) {
        notesDao.addTag(tag)
    }

    override suspend fun deleteTag(id: Int) {
        notesDao.deleteTag(id)
    }
}