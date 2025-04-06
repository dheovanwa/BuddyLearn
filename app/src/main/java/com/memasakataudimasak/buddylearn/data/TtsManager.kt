package com.memasakataudimasak.buddylearn.data

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

class TtsManager(
    private val context: Context,
    private val activity: Activity
) {
    private lateinit var tts: TextToSpeech

    init {
        tts = TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) {
                tts.language = Locale("id", "ID")
            }
        }
    }

    fun returnTts(): TextToSpeech {
        return tts
    }

    fun checkMicPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
            != android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                200
            )
        }
    }

    fun startVoiceIntent(): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale("id", "ID"))
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Silakan beri perintah")
        }
    }

}