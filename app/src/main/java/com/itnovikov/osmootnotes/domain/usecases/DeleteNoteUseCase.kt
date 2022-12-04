package com.itnovikov.osmootnotes.domain.usecases

import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NotesRepository) {

    suspend fun deleteNote(id: String) {
        repository.deleteNote(id)
    }
}