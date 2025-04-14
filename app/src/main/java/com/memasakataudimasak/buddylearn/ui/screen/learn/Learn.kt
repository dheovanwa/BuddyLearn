package com.memasakataudimasak.buddylearn.ui.screen.learn

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memasakataudimasak.buddylearn.ui.theme.BuddyLearnTheme
import com.memasakataudimasak.buddylearn.R
import androidx.compose.runtime.getValue

@Composable
fun Learn() {
    var learnList by remember { mutableStateOf<List<LearnUiState>>(emptyList()) }
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        learnList = getLearn()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val currentItem = learnList[currentIndex]
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(id = R.drawable.back_button),
                                contentDescription = "Back button",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Text(text = "Final Exam")
                    }

                    Row {
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(id = R.drawable.hamburger),
                                contentDescription = "Hamburger",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(id = R.drawable.accessibility),
                                contentDescription = "Accessibility",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentItem.title,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    currentItem.value.forEach { text ->
                        Text(text = text, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                    }

//                    Spacer(modifier = Modifier.height(13.1.dp))
//
//                    listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4").forEach { answer ->
//                        Button(
//                            onClick = {},
//                            modifier = Modifier
//                                .shadow(elevation = 2.dp, shape = RoundedCornerShape(32.dp))
//                                .width(372.dp)
//                                .height(90.dp),
//                            shape = RoundedCornerShape(32),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color(0x03B7B7B7),
//                                contentColor = Color.Black
//                            )
//                        ) {
//                            Text(text = answer)
//                        }
//                        Spacer(modifier = Modifier.height(16.dp))
//                    }
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
                        if (currentIndex > 0) currentIndex--
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
                    if (currentIndex < learnList.size - 1) currentIndex++
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    BuddyLearnTheme {
        Learn()
    }
}