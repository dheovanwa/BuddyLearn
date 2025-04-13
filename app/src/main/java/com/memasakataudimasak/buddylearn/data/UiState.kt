package com.memasakataudimasak.buddylearn.data

data class UiState(
    val grade: Int = 1,
    val isEnglish: Boolean = true,

    val sliderGradeValue: Float = 1f,
    val showSaveDialog: Boolean = false,
)