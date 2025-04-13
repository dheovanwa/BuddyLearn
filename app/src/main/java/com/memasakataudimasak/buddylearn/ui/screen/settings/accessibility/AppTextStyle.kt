package com.memasakataudimasak.buddylearn.ui.screen.settings.accessibility

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class AppTextStyle(
    val fontSize : TextUnit = 18.sp,
    val fontWeight: FontWeight = FontWeight.Normal
)

val LocalAppTextStyle = compositionLocalOf { AppTextStyle() }
