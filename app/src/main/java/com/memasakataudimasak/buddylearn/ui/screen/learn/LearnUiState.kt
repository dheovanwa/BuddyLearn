package com.memasakataudimasak.buddylearn.ui.screen.learn

data class LearnUiState (
    val sectionTitle: String = "",
    val title: String = "",
    val isQuestion: Boolean = false,
    val value: List<String> = emptyList()
)