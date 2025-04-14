package com.memasakataudimasak.buddylearn.ui.screen.settings.accessibility

data class AccessibilityUiState(
    val fontWeights: Int = 2,
    val fontSize: Int = 16,
    val fontFamily: String = "sans",
    val layout: String = "compact",
    val theme: String = "light",
    val isSpeechRecognitionOn: Boolean = true,

    val textSizeSliderValue: Float = 14f,
    val textWeightSliderValue: Float = 2f,
)
