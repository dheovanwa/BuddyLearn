package com.memasakataudimasak.buddylearn.ui.screen.settings.accessibility

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memasakataudimasak.buddylearn.R
import com.memasakataudimasak.buddylearn.ViewModel
import com.memasakataudimasak.buddylearn.ui.theme.BuddyLearnTheme

@Composable
fun CustomSlider(
    viewModel: AccessibilityViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var sliderValue by remember { mutableFloatStateOf(uiState.fontSize.toFloat()) } // Start at 16

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 14..22) {
                if(i == 14){
                    Text(
                        text = i.toString(),
                        fontSize = 12.sp,
                    )
                }else if (i == 22) {
                    // Last item - Align end
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = i.toString(),
                        fontSize = 12.sp,
                    )
                } else {
                    // Middle items - Align center (using Spacer to center the items)
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = i.toString(),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Slider with ticks
        Slider(
            value = uiState.textSizeSliderValue,
            onValueChange = {
                sliderValue = it
                viewModel.setTextSizeSliderValue(sliderValue)
            },
            valueRange = 0f..7f,
            steps = 6, // 8 steps means 7 in-between steps
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                activeTrackColor = Color(0xFF66E0C2),
                inactiveTrackColor = Color(0xFFE0E0E0),
                thumbColor = when {
                    (uiState.theme == "light") -> Color.Black
                    (uiState.theme == "warm") -> Color.Black
                    (uiState.theme == "cold") -> Color.Black
                    (uiState.theme == "dark") -> Color(0xFFF2F6F6)
                    else -> Color.Black // Default behavior for other themes
                }
            )
        )
    }
}

@Composable
fun CustomSliderWeight(
    viewModel: AccessibilityViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var sliderValue by remember { mutableFloatStateOf(uiState.fontWeights.toFloat()) } // Start at 2

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 1..4) {
                // Define different FontWeight values based on the loop index
                val fontWeights = when (i) {
                    1 -> FontWeight.Light
                    2 -> FontWeight.Normal
                    3 -> FontWeight.Medium
                    4 -> FontWeight.Bold
                    else -> FontWeight.Normal
                }

                if (i == 1) {
                    // First item - Align start
                    Text(
                            text = "Aa",
                            fontSize = 12.sp,
                            fontWeight = fontWeights,
                        )
                } else if (i == 4) {
                    // Last item - Align end
                    Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Aa",
                            fontSize = 12.sp,
                            fontWeight = fontWeights,
                        )
//                    }
                } else {
                    // Middle items - Align center (using Spacer to center the items)
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Aa",
                            fontSize = 12.sp,
                            fontWeight = fontWeights
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Slider with ticks
        Slider(
            value = uiState.textWeightSliderValue,
            onValueChange = {
                sliderValue = it
                viewModel.setTextWeightSliderValue(sliderValue)
            },
            valueRange = 0f..4f,
            steps = 2, // 2 steps means 2 steps
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                activeTrackColor = Color(0xFF66E0C2),
                inactiveTrackColor = Color(0xFFE0E0E0),
                thumbColor = when {
                    (uiState.theme == "light") -> Color.Black
                    (uiState.theme == "warm") -> Color.Black
                    (uiState.theme == "cold") -> Color.Black
                    (uiState.theme == "dark") -> Color(0xFFF2F6F6)
                    else -> Color.Black // Default behavior for other themes
                }
            )
        )
    }
}

@Composable
fun AccessibilityScreen(
    viewModel: AccessibilityViewModel

) {
    // Retrieve the previously selected theme from SharedPreferences, or default to "light"
    val sharedPreferences = LocalContext.current.getSharedPreferences("app_prefs", Activity.MODE_PRIVATE)
    val savedTheme = sharedPreferences.getString("selected_theme", "light") ?: "light"
    val savedFontFamily = sharedPreferences.getString("selected_font_family", "sans") ?: "sans"
    val savedLayout = sharedPreferences.getString("selected_layout", "compact") ?: "compact"

    val uiState by viewModel.uiState.collectAsState()

    var selectedTheme by remember { mutableStateOf(savedTheme)}
    var selectedFontFamily by remember { mutableStateOf(savedFontFamily)}
    var selectedLayout by remember { mutableStateOf(savedLayout)}

    fun saveSelectedTheme(theme: String) {
        selectedTheme = theme
        sharedPreferences.edit().putString("selected_theme", theme).apply()
        Log.d("theme main setting", theme)

        viewModel.setTheme(selectedTheme)
    }

    fun saveSelectedFont(font: String) {
        selectedFontFamily = font
        sharedPreferences.edit().putString("selected_font", font).apply()
    }

    fun saveSelectedLayout(layout: String) {
        selectedFontFamily = layout
        sharedPreferences.edit().putString("selected_layout", layout).apply()
    }

    fun selectedButtonTheme(theme: String): Modifier {
        return Modifier
//            .padding(8.dp)
            .border(
                width = 2.dp,
                color = if (selectedTheme == theme) Color(0xFF11BE19) else Color(0xFFE4E4E7), // Green for selected, Gray for others
                shape = RoundedCornerShape(16.dp)
            )
            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
    }

    fun selectedButtonFont(fontFamily: String): Modifier {
        return Modifier
//            .padding(8.dp)
            .border(
                width = 2.dp,
                color = if (selectedFontFamily == fontFamily) Color(0xFF11BE19) else Color(
                    0xFFE4E4E7
                ), // Green for selected, Gray for others
                shape = RoundedCornerShape(16.dp)
            )
            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
    }

    fun selectedButtonLayout(layout: String): Modifier {
        return Modifier
//            .padding(8.dp)
            .border(
                width = 2.dp,
                color = if (selectedLayout == layout) Color(0xFF11BE19) else Color(0xFFE4E4E7), // Green for selected, Gray for others
                shape = RoundedCornerShape(16.dp)
            )
            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
    }


    Column(Modifier
        .fillMaxSize()
        .padding(
            start = 20.dp,
            end = 20.dp
        )
        .verticalScroll(rememberScrollState())
    ) {
        BuddyLearnTheme(selectedTheme = selectedTheme) {
            // The inner Column inside the scrollable Box
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                // Title or Text at the top
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .padding(top = 10.dp, bottom = 10.dp),
                ) {
                    Text(
                        text = "Color Theme",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        //                      fontFamily = R.font.monda,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Column(Modifier
                    .fillMaxWidth()
                    .height(340.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 12.dp, bottom = 12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { saveSelectedTheme("light") },
                                modifier = selectedButtonTheme("light")
                                    .height(120.dp)
                                    .width(132.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFFFFF),
                                    contentColor = Color(0xFF363636)
                                ),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text(
                                    "Hello from the light!",
                                    color = Color(0xFF363636),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Light",
                                color = MaterialTheme.colorScheme.onBackground,
//                                  fontWeight = medium,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }

//                            Spacer(modifier = Modifier.width(36.dp))
//                            Box(modifier = Modifier.padding(end = 12.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(end = 12.dp, bottom = 12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { saveSelectedTheme("dark") },
                                modifier = selectedButtonTheme("dark")
                                    .height(120.dp)
                                    .width(132.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF363636),
                                    contentColor = Color(0xFF363636)
                                ),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text(
                                    "Hello from the Dark!",
                                    color = Color(0xFFFBFBFB),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Cold",
                                color = MaterialTheme.colorScheme.onBackground,
//                                  fontWeight = medium,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 12.dp, bottom = 12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { saveSelectedTheme("warm") },
                                modifier = selectedButtonTheme("warm")
                                    .height(120.dp)
                                    .width(132.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFAEDD3),
                                    contentColor = Color(0xFF363636)
                                ),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text(
                                    "Hello from the Sun!",
                                    color = Color(0xFF363636),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Warm",
                                color = MaterialTheme.colorScheme.onBackground,
//                                  fontWeight = medium,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }

//                            Spacer(modifier = Modifier.width(36.dp))
//                            Box(modifier = Modifier.padding(end = 12.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(end = 12.dp, bottom = 12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { saveSelectedTheme("cold") },
                                modifier = selectedButtonTheme("cold")
                                    .height(120.dp)
                                    .width(132.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFE8EDFC),
                                    contentColor = Color(0xFF363636)
                                ),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text(
                                    "Hello from the Cold!",
                                    color = Color(0xFF363636),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Dark",
                                color = MaterialTheme.colorScheme.onBackground,
//                                  fontWeight = medium,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Speech Recognition",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.monda)),
            )
            Switch(
                checked = uiState.isSpeechRecognitionOn,
                onCheckedChange = {
                    viewModel.setIsSpeechRecognitionOn(it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),

                thumbContent = if (uiState.isSpeechRecognitionOn) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                            tint = Color(0xFFFEFEFE)
                        )
                    }
                } else {
                    {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                }
            )
        }

        Column(Modifier
            .fillMaxWidth()
            .height(120.dp)) {
            // Title or Text at the top
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                Text(
                    text = "Text Font",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    //                      fontFamily = R.font.monda,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { saveSelectedFont("sans") },
                    modifier = selectedButtonFont("sans")
                        .height(60.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color(0xFF363636)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Sans Serif",
                        color = Color(0xFF363636),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
//                                fontFamily = FontFamily(R.font.roboto.medium))
                    )
                }

                Button(
                    onClick = { saveSelectedFont("serif") },
                    modifier = selectedButtonFont("serif")
                        .height(60.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color(0xFF363636)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Serif",
                        color = Color(0xFF363636),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.georgia))
                    )
                }

                Button(
                    onClick = { saveSelectedFont("dyslexic") },
                    modifier = selectedButtonFont("dyslexic")
                        .height(60.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color(0xFF363636)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Open Dyslexic",
                        color = Color(0xFF363636),
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.open_dyslexic))
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 12.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Text Size",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                //                      fontFamily = R.font.monda,
                modifier = Modifier.fillMaxWidth(),
            )
            CustomSlider(viewModel)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 12.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Text Weight",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                //                      fontFamily = R.font.monda,
                modifier = Modifier.fillMaxWidth(),
            )
            CustomSliderWeight(viewModel)
        }

        Column(Modifier
            .fillMaxWidth()
            .height(120.dp)) {
            // Title or Text at the top
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                Text(
                    text = "Reading Layout",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    //                      fontFamily = R.font.monda,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { saveSelectedLayout("compact") },
                    modifier = selectedButtonLayout("compact")
                        .height(60.dp)
                        .width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color(0xFF363636)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.compact_layout),
                        contentDescription = "Wide Layout", // A description for accessibility
                        tint = Color(0xFF363636),
                    )
                }

//                Spacer(Modifier.width(44.dp))

                Button(
                    onClick = { saveSelectedFont("wide") },
                    modifier = selectedButtonTheme("wide")
                        .height(60.dp)
                        .width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color(0xFF363636)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.wide_layout),
                        contentDescription = "Wide Layout", // A description for accessibility
                        tint = Color(0xFF363636),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AccessibilityScreenPreview(){
    var selectedTheme by remember { mutableStateOf("light")}
//    selectedTheme = selectedTheme, onThemeChange = {newTheme -> selectedTheme = newTheme}
    AccessibilityScreen(viewModel = viewModel()
    )
}