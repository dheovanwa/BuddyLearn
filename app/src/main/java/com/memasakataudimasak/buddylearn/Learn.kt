package com.memasakataudimasak.buddylearn

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.memasakataudimasak.buddylearn.ui.theme.BuddyLearnTheme

@Composable
fun Learn() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 70.dp) // Make space for bottom nav
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
                Text("Question 1")
                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .verticalScroll(rememberScrollState())
                        .border(1.dp, Color.Gray)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Lorem ipsum dolor sit amet consectetur. Diam libero viverra magna nunc..." +
                                " Ultrices et magna aliquet vel. Donec fringilla adipiscing ac orci consectetur blandit.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(13.1.dp))

                listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4").forEach { answer ->
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(32.dp))
                            .width(372.dp)
                            .height(90.dp),
                        shape = RoundedCornerShape(32),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x03B7B7B7),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = answer)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
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
                Text(text = "Previous Question")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Next Question")
                IconButton(onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.next_button),
                        contentDescription = "Next button",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
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