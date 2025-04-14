package com.memasakataudimasak.buddylearn.data

data class LearnItem(
    val title: String = "",
    val value: List<String> = emptyList<String>(),
    val questionFlag: Boolean = false,
    val sectionTitle: String = "",

    val question: String = "",
    val answer: Int = 0,
)
