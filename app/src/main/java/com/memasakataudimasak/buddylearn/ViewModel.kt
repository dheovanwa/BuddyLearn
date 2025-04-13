package com.memasakataudimasak.buddylearn

import android.util.Log
import androidx.lifecycle.ViewModel
import com.memasakataudimasak.buddylearn.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        UiState()
    )
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setGrade(grade: Int){
        _uiState.update { currentState ->
            currentState.copy(
                grade = grade
            )
        }
    }

    fun setSettings(grade: Int, isEnglish: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                grade = grade,
                isEnglish = isEnglish,
            )
        }
    }

    fun setShowSaveDialog(showSaveDialog: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                showSaveDialog = showSaveDialog
            )
        }
    }

    fun setIsEnglish(isEnglish: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isEnglish = isEnglish
            )
        }
    }

    fun setSliderGradeValue(sliderGradeValue: Float) {
        _uiState.update { currentState ->
            currentState.copy(
                sliderGradeValue = sliderGradeValue
            )
        }
    }

    fun setUserCommand(userCommand: String) {
        _uiState.update { currentState ->
            currentState.copy(
                userCommand = userCommand
            )
        }
    }
    fun setCommandProcessed(commandProcessed: String) {
        _uiState.update { currentState ->
            currentState.copy(
                commandProcessed = commandProcessed
            )
        }
    }

    fun getGradeAndLanguage(): Pair<Int, Boolean>  {
        val state = _uiState.value
        return Pair(state.grade, state.isEnglish)
    }
}