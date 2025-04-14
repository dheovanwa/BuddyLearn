package com.memasakataudimasak.buddylearn.ui.screen.learn

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memasakataudimasak.buddylearn.R
import com.memasakataudimasak.buddylearn.ViewModel
import com.memasakataudimasak.buddylearn.data.TtsManager
import com.memasakataudimasak.buddylearn.speakOnLongPress

@Composable
fun Learn(
    learnViewModel: LearnViewModel = viewModel(),
    globalViewModel: ViewModel = viewModel(),
    ttsManager: TtsManager,
    ) {
//    var learnList by remember { mutableStateOf<List<LearnUiState>>(emptyList()) }
//    var currentIndex by remember { mutableStateOf(0) }
    val learnUiState by learnViewModel.uiState.collectAsState()
    val globalUiState by globalViewModel.uiState.collectAsState()

    val learnList = learnUiState.learnList
    val currentIndex = learnUiState.currentIndex

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
//        currentItem = learnList[currentIndex]
        val currentItem = learnUiState.learnList.getOrNull(learnUiState.currentIndex)
        val scrollState = rememberScrollState()

        if (learnList.isNotEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentItem?.title.toString(),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Log.d("learn content", currentItem.toString())

                    if (currentItem?.questionFlag == false) {
                        currentItem?.value?.forEach { text ->
                            Text(
                                text,
                                modifier = Modifier
                                    .speakOnLongPress(tts = ttsManager.returnTts(), ttsEnabled = globalUiState.onTextToSpeech, text = text)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                    } else {
                        Text(
                            text = currentItem?.sectionTitle.toString(),
//                            style = MaterialTheme.typography.headlineMedium
                        )

                        var selectedIndex by remember { mutableStateOf<Int?>(null) }
                        val correctAnswerIndex = currentItem?.answer ?: 0 // atau ambil dari LearnItem

                        currentItem?.value?.forEachIndexed { index, option ->
                            val isSelected = selectedIndex == index
                            val isCorrect = index == correctAnswerIndex
                            val isAnswered = selectedIndex != null

                            val backgroundColor = when {
                                !isAnswered -> Color(0x03B7B7B7) // default
                                isSelected && isCorrect -> Color(0xFF4CAF50) // green
                                isSelected && !isCorrect -> Color(0xFFF44336) // red
                                else -> Color(0x03B7B7B7) // others stay default
                            }

                            Button(
                                onClick = {
                                    if (selectedIndex == null) {
                                        selectedIndex = index
                                    }
                                },
                                modifier = Modifier
                                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(32.dp))
                                    .width(372.dp)
                                    .height(90.dp),
                                shape = RoundedCornerShape(32),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = backgroundColor,
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(option)
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        if (currentIndex > 0) learnViewModel.setCurrentIndex(currentIndex-1)
                    },
                    enabled = currentIndex > 0) {
                    Image(
                        painter = painterResource(id = R.drawable.back_button),
                        contentDescription = "Back button",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = "Previous Question")
                }

                IconButton(onClick = {
                    if (currentIndex < learnList.size - 1) learnViewModel.setCurrentIndex(currentIndex+1)
                },
                enabled = currentIndex < learnList.size - 1) {
                    Text(text = "Next Question")
                    Image(
                        painter = painterResource(id = R.drawable.next_button),
                        contentDescription = "Next button",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        } else {
            Text(
                text = "Loading...",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}