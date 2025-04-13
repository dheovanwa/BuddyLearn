package com.memasakataudimasak.buddylearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.memasakataudimasak.buddylearn.ui.theme.BuddyLearnTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.memasakataudimasak.buddylearn.ui.screen.settings.accessibility.Accessibility
import com.memasakataudimasak.buddylearn.ui.screen.settings.accessibility.AppTextStyle
import com.memasakataudimasak.buddylearn.ui.theme.ColdColors
import com.memasakataudimasak.buddylearn.ui.theme.DarkColors
import com.memasakataudimasak.buddylearn.ui.theme.LightColors
import com.memasakataudimasak.buddylearn.ui.theme.WarmColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var theme by remember { mutableStateOf("light") }

            // Select the color scheme based on the current theme
            val colors = when (theme) {
                "dark" -> DarkColors
                "warm" -> WarmColors
                "cold" -> ColdColors
                else -> LightColors // Default to Light theme
            }

            // Apply the selected color scheme to the MaterialTheme
            MaterialTheme(colorScheme = colors) {
                Accessibility(onThemeChange = { newTheme -> theme = newTheme })
                var textStyle by remember {
                    mutableStateOf(AppTextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal))
                }
                Accessibility(onThemeChange = { newTheme -> theme = newTheme })
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    BuddyLearnTheme {
//        Greeting("Android")
//    }
//}