package com.itnovikov.osmootnotes.domain.usecases

import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NotesRepository) {

    suspend fun addNote(note: Note) {
        repository.addNote(note)
    }
}