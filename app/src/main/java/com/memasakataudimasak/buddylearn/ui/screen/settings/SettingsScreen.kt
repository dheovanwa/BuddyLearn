package com.memasakataudimasak.buddylearn.ui.screen.settings

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memasakataudimasak.buddylearn.AuthState
import com.memasakataudimasak.buddylearn.AuthViewModel
import com.memasakataudimasak.buddylearn.R
import com.memasakataudimasak.buddylearn.ViewModel
import com.memasakataudimasak.buddylearn.ui.theme.BuddyLearnTheme

@Composable
fun LanguageDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onLanguageSelected: (String) -> Unit,
    viewModel: ViewModel
) {
    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Surface(
                shape = RoundedCornerShape(3.dp),
                shadowElevation = 8.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Bahasa Indonesia",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onLanguageSelected("Bahasa Indonesia")
                                viewModel.setIsEnglish(false)
                                onDismiss()
                            }
                            .padding(12.dp),
                        fontSize = 16.sp
                    )
                    Text(
                        text = "English",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onLanguageSelected("English")
                                viewModel.setIsEnglish(true)
                                onDismiss()
                            }
                            .padding(12.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = (size.height - strokeWidth / 2) + 14
                drawLine(
                    color = Color(red = 185, green = 185, blue = 185),
                    start = Offset(x = 0f, y = y),
                    end = Offset(x = size.width, y = y),
                    strokeWidth = strokeWidth
                )
            }
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun SettingsScreen(
    grade: Int = 1,
    isEnglish: Boolean = true,
    onAccessibilitySettingsClicked: () -> Unit = {},
    onGradeSettingClicked: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    onSignOut: () -> Unit = {},
    viewModel: ViewModel,
) {
    var selectedLanguage by remember { mutableStateOf("English") }
    var showDialog by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column {
            SettingsRow(
                onClick = onAccessibilitySettingsClicked
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_accessibility_new_24),
                        contentDescription = stringResource(R.string.access_accessibility_settings_content_desc),
                    )
                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )
                    Text(
                        "Accessibility",
                        fontFamily = FontFamily(Font(R.font.monda))
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_right_24),
                    contentDescription = stringResource(R.string.access_accessibility_settings_content_desc),
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }
            SettingsRow(
                onClick = onGradeSettingClicked
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.school_24px),
                        contentDescription = stringResource(R.string.access_accessibility_settings_content_desc),
                    )
                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )
                    Text(
                        "Grade",
                        fontFamily = FontFamily(Font(R.font.monda))
                    )
                }
                Text(
                    "Grade $grade",
                    fontFamily = FontFamily(Font(R.font.monda)),
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }
            SettingsRow(
                onClick = { showDialog = true }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.language_24px),
                        contentDescription = stringResource(R.string.access_accessibility_settings_content_desc),
                    )
                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )
                    Text(
                        "Voice Language",
                        fontFamily = FontFamily(Font(R.font.monda))
                    )
                }
                Text(
                    text = if (uiState.isEnglish) "English" else "Indonesia",
                    fontFamily = FontFamily(Font(R.font.monda)),
                    modifier = Modifier.padding(bottom = 5.dp),
                )

            }
            LanguageDialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                onLanguageSelected = { selectedLanguage = it },
                viewModel = viewModel,
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = onSignOut,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .drawBehind {
                        val borderSize = 1.dp.toPx()
                        drawLine(
                            color = Color.Gray,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = borderSize
                        )
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFFF6F6F)
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    "Logout",
                    fontFamily = FontFamily(Font(R.font.monda))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        grade = 1,
        viewModel = ViewModel(),
    )
}