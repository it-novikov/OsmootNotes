package com.itnovikov.osmootnotes.domain.usecases

import com.itnovikov.osmootnotes.data.local.room.model.Tag
import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import javax.inject.Inject

class AddTagUseCase @Inject constructor(private val repository: NotesRepository) {

    suspend fun addTag(tag: Tag) {
        repository.addTag(tag)
    }
}