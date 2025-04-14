package com.memasakataudimasak.buddylearn.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column (
        modifier = Modifier
        .fillMaxSize()
        .padding(
            start = 20.dp,
            end = 20.dp)
        .verticalScroll(rememberScrollState())
    ){
        Text("Last Learned",
            fontSize = 16.sp)
    }
}