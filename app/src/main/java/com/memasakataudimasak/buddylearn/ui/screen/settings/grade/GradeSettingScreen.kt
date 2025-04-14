package com.memasakataudimasak.buddylearn.ui.screen.settings.grade

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memasakataudimasak.buddylearn.R
import com.memasakataudimasak.buddylearn.ViewModel

@Composable
fun CustomSlider(
    viewModel: ViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var sliderValue by remember { mutableFloatStateOf(uiState.grade.toFloat()) } // Start at 4

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = uiState.sliderGradeValue,
            onValueChange = {
                sliderValue = it
                viewModel.setSliderGradeValue(sliderValue)
                            },
            valueRange = 0f..12f,
            steps = 11, // 8 steps means 7 in-between steps
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                activeTrackColor = Color(0xFF66E0C2),
            )
        )
    }
}

@Composable
fun GradeSettingScreen(
    currentGrade: Int = 1,
    ischanged: Boolean = false,
    viewModel: ViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.grade_decoration_image
            ),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .padding(8.dp, top = 10.dp)
        )
        Text(
            "Grade ${uiState.sliderGradeValue.toInt()}",
            fontFamily = FontFamily(Font(R.font.monda)),
            fontWeight = FontWeight.W500,
            fontSize = 50.sp
        )
        CustomSlider(viewModel)

        if (uiState.sliderGradeValue.toInt() != uiState.grade) {
            Button(
                onClick = {viewModel.setGrade(uiState.sliderGradeValue.toInt())},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8BC34A),
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 12.dp
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
                    .padding(top = 15.dp)
            ) {
                Text(
                    "Change",
                    fontFamily = FontFamily(Font(R.font.monda)),
                    fontSize = 20.sp
                )
            }
        } else {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDCDCDC),
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 12.dp
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
                    .padding(top = 15.dp)
            ) {
                Text(
                    "Change",
                    fontFamily = FontFamily(Font(R.font.monda)),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GradeSettingScreenPreview() {
    GradeSettingScreen(
        viewModel = ViewModel()
    )
}