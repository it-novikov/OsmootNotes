package com.itnovikov.osmootnotes.domain.usecases

import androidx.lifecycle.LiveData
import com.itnovikov.osmootnotes.data.local.room.model.Tag
import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import javax.inject.Inject

class GetTagsUseCase @Inject constructor(private val repository: NotesRepository) {

    fun getTags(): LiveData<List<Tag>> {
        return repository.getTags()
    }
}