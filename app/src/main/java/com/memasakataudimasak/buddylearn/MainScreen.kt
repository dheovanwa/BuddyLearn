package com.memasakataudimasak.buddylearn

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.MotionEvent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.memasakataudimasak.buddylearn.data.TtsManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.collections.firstOrNull
import kotlin.text.lowercase

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    context: Context,
    activity: Activity
) {
    val navController = rememberNavController()

    val ttsManager = TtsManager(
        context,
        activity
    )

    TtsBox(
        tts = ttsManager.returnTts(),
        startVoiceIntent = { ttsManager.startVoiceIntent() },
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.name) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Ini adalah Home Screen")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { navController.navigate(Screen.Settings.name) }) {
                            Text("Go to Settings")
                        }
                    }
                }
                composable(Screen.Settings.name) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Ini adalah Settings Screen")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { navController.navigate(Screen.Home.name) }) {
                            Text("Go to Home")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TtsBox(
    tts: TextToSpeech,
    startVoiceIntent: () -> Intent,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var longPressJob by remember { mutableStateOf<Job?>(null) }
    var isFingerDown by remember { mutableStateOf(false) }
    val hasLaunched = remember { mutableStateOf(false) }

    val voiceLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        hasLaunched.value = false // Reset di sini setelah selesai bicara!

        if (result.resultCode == RESULT_OK) {
            val spokenText = result.data
                ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                ?.firstOrNull()
                ?.lowercase(Locale.ROOT)
        }
    }

    Box(
        modifier = modifier
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        isFingerDown = true
                        longPressJob?.cancel()
                        longPressJob = coroutineScope.launch {
                            delay(1000L)
                            if (isFingerDown) {
                                tts.speak(
                                    "Asisten suara aktif. Katakan Home atau Settings",
                                    TextToSpeech.QUEUE_FLUSH,
                                    null,
                                    null
                                )
                                voiceLauncher.launch(startVoiceIntent())
                            }
                        }
                        false
                    }

                    MotionEvent.ACTION_UP,
                    MotionEvent.ACTION_CANCEL -> {
                        isFingerDown = false
                        longPressJob?.cancel()
                        longPressJob = null
                        false
                    }

                    else -> false
                }
            }
    ) {
        content()
    }

}