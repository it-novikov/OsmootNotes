package com.itnovikov.osmootnotes.presentation.newnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.data.local.room.model.Tag
import com.itnovikov.osmootnotes.domain.usecases.AddNoteUseCase
import com.itnovikov.osmootnotes.domain.usecases.AddTagUseCase
import com.itnovikov.osmootnotes.domain.usecases.GetTagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    getTagsUseCase: GetTagsUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val addTagUseCase: AddTagUseCase
) : ViewModel() {

    val tags: LiveData<List<Tag>> = getTagsUseCase.getTags()

    private val shouldCloseScreen = MutableLiveData<Boolean>()
    private val tagsArray = arrayListOf<Tag>()

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase.addNote(note)
            shouldCloseScreen.postValue(true)
        }
    }

    fun getShouldCloseScreen(): LiveData<Boolean> {
        return shouldCloseScreen
    }

    fun addTagToDatabase(tag: Tag) {
        viewModelScope.launch { addTagUseCase.addTag(tag) }
    }

    fun getTagsFromDatabase(): List<String> {
        val namesList = arrayListOf<String>()
        if (tags.value != null) {
            for (tag in tags.value!!) {
                namesList.add(tag.name)
            }
        }
        return namesList
    }

    fun clickTag(tag: Tag, isClicked: Boolean) {
        tag.isClicked = isClicked
    }

    fun newTagToNote(tag: Tag) {
        tagsArray.add(tag)
    }

    fun removeTagFromNote(tag: Tag) {
        tagsArray.remove(tag)
    }

    fun getNoteTagsList(): String {
        val arrayOfNames = arrayListOf<String>()
        for (tag in tagsArray) {
            arrayOfNames.add(tag.name)
        }
        return "  " + arrayOfNames.joinToString(", ")
    }

    fun getCurrentDate(): String {
        val currentDate = LocalDateTime.now()
        val formatter = if (Locale.getDefault().language == "en") {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        } else {
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        }
        return currentDate.format(formatter).toString()
    }
}