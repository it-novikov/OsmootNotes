package com.itnovikov.osmootnotes.presentation.detailednote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itnovikov.osmootnotes.domain.usecases.DeleteNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedNoteViewModel @Inject constructor(

    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private var shouldCloseScreen: MutableLiveData<Boolean> = MutableLiveData()

    fun deleteNote(id: String) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(id)
            shouldCloseScreen.postValue(true)
        }
    }

    fun getShouldCloseScreen(): LiveData<Boolean> {
        return shouldCloseScreen
    }
}