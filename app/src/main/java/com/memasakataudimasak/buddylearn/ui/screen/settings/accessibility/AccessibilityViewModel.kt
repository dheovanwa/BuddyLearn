package com.memasakataudimasak.buddylearn.ui.screen.settings.accessibility

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AccessibilityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        AccessibilityUiState()
    )
    val uiState: StateFlow<AccessibilityUiState> = _uiState.asStateFlow()

    fun setIsSpeechRecognitionOn(isSpeechRecognitionOn: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isSpeechRecognitionOn = isSpeechRecognitionOn
            )
        }
    }

    fun setTextSizeSliderValue(textSizeSliderValue: Float) {
        _uiState.update { currentState ->
            currentState.copy(
                textSizeSliderValue = textSizeSliderValue
            )
        }
    }

    fun setTextWeightSliderValue(textWeightSliderValue: Float) {
        _uiState.update { currentState ->
            currentState.copy(
                textWeightSliderValue = textWeightSliderValue
            )
        }
    }
    fun setTheme(theme: String) {
        _uiState.update { currentState ->
            currentState.copy(
                theme = theme
            )
        }
//        Log.d("theme main di viewmodel", uiState.value.theme)

    }

    fun setFontFamily(fontFamily: String) {
        _uiState.update { currentState ->
            currentState.copy(
                fontFamily = fontFamily
            )
        }
//        Log.d("theme main di viewmodel", uiState.value.theme)

    }

}