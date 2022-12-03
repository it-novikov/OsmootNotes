package com.itnovikov.osmootnotes.domain.usecases

import androidx.lifecycle.LiveData
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val repository: NotesRepository) {

    fun getNotes(): LiveData<List<Note>> {
        return repository.getNotes()
    }
}