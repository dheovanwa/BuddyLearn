package com.memasakataudimasak.buddylearn.ui.screen.learn

import com.google.firebase.firestore.PropertyName
import com.memasakataudimasak.buddylearn.data.LearnItem

data class LearnUiState (
    val learnList: List<LearnItem> = emptyList(),
    val currentIndex: Int = 0,
    val sectionTitle: String = "",
    val title: String = "",
    val value: List<String> = emptyList()
)