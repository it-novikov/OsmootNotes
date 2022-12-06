package com.itnovikov.osmootnotes.domain.usecases

import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import javax.inject.Inject

class GetDetailedNoteUseCase @Inject constructor(private val repository: NotesRepository) {

    suspend fun getDetailedNote(id: String): Note {
        return repository.getDetailedNote(id)
    }
}