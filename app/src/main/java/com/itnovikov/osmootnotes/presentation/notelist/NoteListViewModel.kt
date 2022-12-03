package com.itnovikov.osmootnotes.presentation.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.data.local.room.model.Tag
import com.itnovikov.osmootnotes.domain.usecases.GetNotesUseCase
import com.itnovikov.osmootnotes.domain.usecases.GetTagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    getNotesUseCase: GetNotesUseCase,
    getTagsUseCase: GetTagsUseCase
) : ViewModel() {

    val notes: LiveData<List<Note>> = getNotesUseCase.getNotes()
    val tagFilters: LiveData<List<Tag>> = getTagsUseCase.getTags()

    fun clickTag(tag: Tag, isClicked: Boolean) {
        tag.isClicked = isClicked
    }
}