package com.memasakataudimasak.buddylearn.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
//import androidx.compose.material.lightColors
//import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)

// Light Theme Colors
val LightThemeColors = lightColorScheme(
    primary = Color(0xFFF2F6F6),
    secondary = Color(0xFF9B9898),
    background = Color.White,
    primaryContainer = Color(0xFFE8EBEB),
    secondaryContainer = Color(0xFFCECDCD),
    surface = Color(0xFFFAFAFA),
    onPrimary = Color(0xFF363636),
    onSecondary = Color(0xFF363636), // Text on secondary color
    onBackground = Color(0xFF363636), // Text on white background (dark text)
    onSurface = Color(0xFF363636) // Text on light surfaces (dark text)
)

// Dark Theme Colors
val DarkThemeColors = darkColorScheme(
    primary = Color(0xFF1B1A18),
    secondary = Color(0xFF5D5C5B),
    primaryContainer = Color(0xFF5A5662),
    secondaryContainer  = Color(0xFF464549),
    background = Color(0xFF363636),
    surface = Color(0xFF474747),
    onPrimary = Color(0xFFFBFBFE),
    onSecondary = Color(0xFFFBFBFE),
    onBackground = Color(0xFFFBFBFE),
    onSurface = Color(0xFFFBFBFE)
)

// Warm Theme Colors
val WarmThemeColors = lightColorScheme(
    primary = Color(0xFFFFC107),
    secondary = Color(0xFFFED78E),
    primaryContainer = Color(0xFFFEEDDB),
    secondaryContainer = Color(0xFFFCF3E6),
    background = Color(0xFFFFF7E8),
    surface = Color(0xFFFFF7E8),
    onPrimary = Color(0xFF363636),
    onSecondary = Color(0xFF363636),
    onBackground = Color(0xFF363636),
    onSurface = Color(0xFF363636)
)

// Cold Theme Colors
val ColdThemeColors = lightColorScheme(
    primary = Color(0xFF0AD3EE),
    secondary = Color(0xFF98C5C1),
    primaryContainer = Color(0xFFCAE2E2),
    secondaryContainer = Color(0xFFB8C7C4),
    background = Color(0xFFEEF2FE),
    surface = Color(0xFFFBFFFF),
    onPrimary = Color(0xFF363636),
    onSecondary = Color(0xFF363636),
    onBackground = Color(0xFF363636),
    onSurface = Color(0xFF363636)
)

private fun l() = 0xFF98C5C1

@Composable
fun BuddyLearnTheme(
    selectedTheme: String? = null,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,

    content: @Composable () -> Unit
) {
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}