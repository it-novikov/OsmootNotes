package com.itnovikov.osmootnotes.domain.usecases

import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteTagUseCase @Inject constructor(private val repository: NotesRepository) {

    suspend fun deleteTag(id: Int) {
        repository.deleteTag(id)
    }
}