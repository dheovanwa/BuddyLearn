package com.memasakataudimasak.buddylearn.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.memasakataudimasak.buddylearn.R


val Sans = FontFamily(Font(R.font.ar_one_sans))
val Serif = FontFamily.Serif
val OpenDyslexic = FontFamily(Font(R.font.open_dyslexic))

fun getTypography(fontFamilyName: String): Typography {
    val selectedFont = when (fontFamilyName.lowercase()) {
        "sans" -> Sans
        "serif" -> Serif
        "opendyslexic" -> OpenDyslexic
        else -> FontFamily.Default
    }

    return Typography(
        bodyLarge = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = selectedFont,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )
}
