package com.memasakataudimasak.buddylearn.data

data class UiState(
    val fontWeights: Int = 2,
    val fontSize: Int = 16,

    val grade: Int = 1,
    val isEnglish: Boolean = true,

    val sliderGradeValue: Float = 1f,
    val showSaveDialog: Boolean = false,

    val userCommand: String = "",
    val commandProcessed: String = "",

    val onTextToSpeech: Boolean = true,
)