package com.memasakataudimasak.buddylearn.ui.screen.settings.accessibility

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memasakataudimasak.buddylearn.ui.screen.settings.SettingsScreen

@Composable
fun Accessibility(selectedTheme: (String), onThemeChange: (String) -> Unit) {
    Column(Modifier.fillMaxHeight()){
        Column(Modifier.height(26.dp).fillMaxWidth()){
            Text("Select Theme", fontSize = 16.sp, fontFamily = "")
        }
        Row(Modifier.fillMaxWidth().height(320.dp)) {
            Text("Select Theme")
            Spacer(Modifier.height(16.5.dp))
            Column{
                Button(
                    onClick = { onThemeChange("dark") }) {
                        Text("Dark Theme")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { onThemeChange("warm") }) {
                    Text("Warm Theme")
                }
            Column {
                Button(onClick = { onThemeChange("light") }) {
                    Text("Light Theme")
                }

                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onThemeChange("cold") }) {
                    Text("Cold Theme")
                }
            }
            Text(text = "Selected Theme: $selectedTheme")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AccessibilityScreenPreview(){
    var selectedTheme by remember { mutableStateOf("light")}

    Accessibility(selectedTheme = selectedTheme, onThemeChange = {newTheme -> selectedTheme = newTheme})
}