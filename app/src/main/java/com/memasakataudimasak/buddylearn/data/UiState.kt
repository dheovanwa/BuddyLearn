package com.memasakataudimasak.buddylearn.data

data class UiState(
    val grade: Int = 7,
    val isEnglish: Boolean = true,

    val sliderGradeValue: Float = 1f,
    val showSaveDialog: Boolean = false,

    val userCommand: String = "",
    val commandProcessed: String = "",

    val onTextToSpeech: Boolean = true,
)