package com.itnovikov.osmootnotes.presentation.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.data.local.room.model.Tag
import com.itnovikov.osmootnotes.domain.usecases.GetNotesUseCase
import com.itnovikov.osmootnotes.domain.usecases.GetTagsUseCase
import com.itnovikov.osmootnotes.presentation.notelist.model.MainFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    getNotesUseCase: GetNotesUseCase,
    getTagsUseCase: GetTagsUseCase
) : ViewModel() {

    val notes: LiveData<List<Note>> = getNotesUseCase.getNotes()
    val tagFilters: LiveData<List<Tag>> = getTagsUseCase.getTags()

    private val _dataFilter = MutableLiveData(MainFilter())
    val dataFilter: LiveData<MainFilter> = _dataFilter

    fun getStartNotes() = notes.value

    fun updateFilter(tag: Pair<Tag, Boolean>) {
        val filter = _dataFilter.value

        if (tag.second) filter?.listTags?.add(tag.first)
        else filter?.listTags?.remove(tag.first)

        _dataFilter.value = filter
    }

    fun clickTag(tag: Tag, isClicked: Boolean) {
        tag.isClicked = isClicked
    }

    fun filteredListNote(filter: MainFilter): List<Note>? {

        val listNote = notes.value
        val listFilterTags: MutableList<Tag> = filter.listTags

        val tagsNames : MutableList<String> = listFilterTags.map { it.name }.toMutableList()

        val data = listNote?.filter { note ->
            val listTags = note.tags.trim().split(", ")

            listTags.any { tag1 -> tagsNames.contains(tag1) }
        }

        return data
    }
}