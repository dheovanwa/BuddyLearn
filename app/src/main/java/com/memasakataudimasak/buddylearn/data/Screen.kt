package com.memasakataudimasak.buddylearn.data

import androidx.annotation.StringRes
import com.memasakataudimasak.buddylearn.R

enum class Screen(@StringRes val title: Int) {
    Home(title = R.string.home_screen),
    Settings(title = R.string.settings_screen),
    Accessibility(title = R.string.accessibility_screen),
    Grade(title = R.string.grade_setting_screen),
    Login(title = R.string.login),
    Signup(title = R.string.signup),
    Landing(title = R.string.landing),
    Learn(title = R.string.learn)
}