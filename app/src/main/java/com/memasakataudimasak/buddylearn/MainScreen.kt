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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.memasakataudimasak.buddylearn.data.Screen
import com.memasakataudimasak.buddylearn.data.TtsManager
import com.memasakataudimasak.buddylearn.ui.screen.settings.SettingsScreen
import com.memasakataudimasak.buddylearn.ui.screen.settings.grade.GradeSettingScreen
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.memasakataudimasak.buddylearn.data.UiState
import com.memasakataudimasak.buddylearn.ui.screen.settings.SaveChangesDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenBar(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon ={
            if (canNavigateBack) {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    context: Context,
    activity: Activity,
    viewModel: ViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Home.name
    )

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
            topBar = {
                MainScreenBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    navController = navController,
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = Screen.Home.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.name) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Ini adalah Home Screen")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            navController.navigate(Screen.Settings.name)
                        }) {
                            Text("Go to Settings")
                        }
                    }
                }
                composable(Screen.Settings.name) {
                    SettingsScreen(
                        grade = uiState.grade,
                        isEnglish = uiState.isEnglish,
                        onGradeSettingClicked = { navController.navigate((Screen.Grade.name)) },
                        onNavigateBack = {navController.popBackStack()},
                        viewModel = viewModel
                    )
                }
                composable(Screen.Grade.name) {
                    GradeSettingScreen(
                        viewModel = viewModel
                    )
                }
            }
        }

//        if (uiState.showSaveDialog) {
//            SaveChangesDialog(
//                showDialog = uiState.showSaveDialog,
//                onDismiss = { viewModel.setShowSaveDialog(false) },
//                onSave = {
//                    viewModel.setShowSaveDialog(false)
//                    navController.popBackStack()
//                },
//                onDontSave = {
//                    viewModel.setShowSaveDialog(false)
//                    navController.popBackStack()
//                }
//            )
//        }

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
        hasLaunched.value = false // Reset di sini setelah selesai bicara

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
                            delay(300000L)
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