package com.memasakataudimasak.buddylearn.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
//import androidx.compose.material.lightColors
//import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.memasakataudimasak.buddylearn.ui.screen.settings.accessibility.AccessibilityViewModel

// Light Theme Colors
val LightThemeColors = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    background = Color.White,
    surface = Color(0xFFDDDDDD),
    onPrimary = Color.White, // Text on primary color (white text on primary color)
    onSecondary = Color(0xFF363636), // Text on secondary color
    onBackground = Color(0xFF363636), // Text on white background (dark text)
    onSurface = Color(0xFF363636) // Text on light surfaces (dark text)
)

// Dark Theme Colors
val DarkThemeColors = darkColorScheme(
    primary = Color(0xFF1A1A1A),
    secondary = Color(0xFF6200EE),
    background = Color(0xFF121212),
    surface = Color(0xFF333333),
    onPrimary = Color(0xFFFBFBFE),
    onSecondary = Color(0xFFFBFBFE),
    onBackground = Color(0xFFFBFBFE),
    onSurface = Color(0xFFFBFBFE)
)

// Warm Theme Colors
val WarmThemeColors = lightColorScheme(
    primary = Color(0xFFFF5722),
    secondary = Color(0xFFFFC107),
    background = Color(0xFFFFF3E0),
    surface = Color(0xFFFFE0B2),
    onPrimary = Color(0xFF363636),
    onSecondary = Color(0xFF363636),
    onBackground = Color(0xFF363636),
    onSurface = Color(0xFF363636)
)

// Cold Theme Colors
val ColdThemeColors = lightColorScheme(
    primary = Color(0xFF00BCD4),
    secondary = Color(0xFF4CAF50),
    background = Color(0xFFE1F5FE),
    surface = Color(0xFFB2EBF2),
    onPrimary = Color(0xFF363636),
    onSecondary = Color(0xFF363636),
    onBackground = Color(0xFF363636),
    onSurface = Color(0xFF363636)
)

@Composable
fun BuddyLearnTheme(
    selectedTheme: String? = null,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    accessibilityViewModel: AccessibilityViewModel = viewModel(),
    content: @Composable () -> Unit
) {
    val uiState by accessibilityViewModel.uiState.collectAsState()

    val isSysteminDarkMode = isSystemInDarkTheme()

    val colorScheme = when {
        selectedTheme != null -> {
            // Use the selected theme (dark, light, warm, cold)
            when (selectedTheme) {
                "dark" -> DarkThemeColors
                "warm" -> WarmThemeColors
                "cold" -> ColdThemeColors
                else -> LightThemeColors // Default to light theme
            }
        }
        isSysteminDarkMode-> {
            DarkThemeColors
        }
        else -> {
            // Use light mode colors if system is in light mode
            LightThemeColors
        }
    }

//    val finalColorScheme = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//        val context = LocalContext.current
//        if (isSysteminDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//    } else {
//        colorScheme
//    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = getTypography(uiState.fontFamily),
        content = content
    )
}