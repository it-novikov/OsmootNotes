package com.itnovikov.osmootnotes.domain.repository

import androidx.lifecycle.LiveData
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.data.local.room.model.Tag

interface NotesRepository {

    fun getNotes(): LiveData<List<Note>>

    suspend fun deleteNote(id: String)
    suspend fun addNote(note: Note)

    fun getTags(): LiveData<List<Tag>>

    suspend fun addTag(tag: Tag)
    suspend fun deleteTag(id: Int)
}