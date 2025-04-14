package com.memasakataudimasak.buddylearn.ui.screen.home

data class HomeUiState(
//    @DrawableRes val image: Int,
    val imageID : Array<Int>,
    val grades : Array<Int>,
    val latestTopic : String,
    val latestSubTopic : String,
    val lessonDuration : Array<Int>,
    val lessonDescriptions: Array<String>,
    val lessonNames : Array<String>
)
