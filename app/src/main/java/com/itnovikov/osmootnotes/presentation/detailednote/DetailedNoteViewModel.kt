package com.itnovikov.osmootnotes.presentation.detailednote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.domain.usecases.DeleteNoteUseCase
import com.itnovikov.osmootnotes.domain.usecases.GetDetailedNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedNoteViewModel @Inject constructor(
    private val getDetailedNoteUseCase: GetDetailedNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private var shouldCloseScreen: MutableLiveData<Boolean> = MutableLiveData()
    private val detailedNote = MutableLiveData<Note>()

    fun deleteNote(id: String) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(id)
            shouldCloseScreen.postValue(true)
        }
    }

    fun getDetailedNote(id: String): LiveData<Note> {
        getDetailedNoteData(id)
        return detailedNote
    }

    fun getShouldCloseScreen(): LiveData<Boolean> {
        return shouldCloseScreen
    }

    private fun getDetailedNoteData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = getDetailedNoteUseCase.getDetailedNote(id)
            detailedNote.postValue(note)
        }
    }
}