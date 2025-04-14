package com.memasakataudimasak.buddylearn

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.MotionEvent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.memasakataudimasak.buddylearn.data.NavigationAssistant
import com.memasakataudimasak.buddylearn.data.UiState
import com.memasakataudimasak.buddylearn.ui.screen.landingpage.LandingPage
import com.memasakataudimasak.buddylearn.ui.screen.login.Login
import com.memasakataudimasak.buddylearn.ui.screen.settings.SaveChangesDialog
import com.memasakataudimasak.buddylearn.ui.screen.signup.Register

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.speakOnLongPress(
    tts: TextToSpeech,
    text: String,
    ttsEnabled: Boolean,
    delayMillis: Long = 500L
): Modifier = composed {
    var isPressed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    this.pointerInteropFilter {
        when (it.action) {
            MotionEvent.ACTION_DOWN -> {
                isPressed = true
                job?.cancel()
                job = scope.launch {
                    delay(delayMillis)
                    if (isPressed && ttsEnabled) {
                        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                }
                false
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isPressed = false
                job?.cancel()
                job = null
                false
            }
            else -> false
        }
    }
}

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
    authViewModel: AuthViewModel = AuthViewModel()
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

    val uiState by viewModel.uiState.collectAsState()

    TtsBox(
        tts = ttsManager.returnTts(),
        viewModel = viewModel,
        currentScreen = currentScreen,
        startVoiceIntent = { ttsManager.startVoiceIntent(uiState.isEnglish) },
        modifier = Modifier.fillMaxSize(),
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

            NavHost(
                navController = navController,
                startDestination = Screen.Landing.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.name) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Ini adalah Home Screen")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            navController.navigate(Screen.Settings.name)
                        },
                            modifier = Modifier
                                .speakOnLongPress(tts = ttsManager.returnTts(), ttsEnabled = uiState.onTextToSpeech, text = "Ini adalah tombol menuju pengaturan")
                        ) {
                            Text("Go to Settings")
                        }
                    }
                }
                composable(Screen.Login.name) {
                    Login(
                        navController = navController,
                        authViewModel = authViewModel
                    )
                }
                composable(Screen.Signup.name) {
                    Register(
                        navController = navController,
                        authViewModel = authViewModel
                    )
                }
                composable(Screen.Landing.name) {
                    LandingPage(
                        authViewModel = authViewModel,
                        navController = navController
                    )
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

            LaunchedEffect(uiState.commandProcessed) {
                val commands = uiState.commandProcessed.split(",")
                val isEnglish = uiState.isEnglish

                when (commands[0]) {
                    "go-to-home-screen" -> {
                        navController.navigate(Screen.Home.name)
                        ttsManager.feedback(if (!isEnglish) "To home page" else "Menuju halaman utama", isEnglish)
                    }
                    "go-to-settings-screen" -> {
                        navController.navigate(Screen.Settings.name)
                        ttsManager.feedback(if (isEnglish) "To settings page" else "Menuju pengaturan", isEnglish)
                    }
                    "change-language" -> {
                        viewModel.setIsEnglish(commands[1].toBoolean())
                        ttsManager.feedback(if (isEnglish) "Changed language to Indonesia" else "Mengubah bahasa menjadi bahasa Inggris", isEnglish)
                    }
                    "change-grade" -> {
                        viewModel.setGrade(commands[1].toInt())
                        ttsManager.feedback(if (isEnglish) "Changed grade to ${commands[1]}" else "Mengubah kelas menjadi kelas ${commands[1]}", isEnglish)
                    }
                    "back" -> navController.navigateUp()
                }

//                Log.d("voice command in main", "${uiState.commandProcessed}")
            }
        }

    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TtsBox(
    tts: TextToSpeech,
    viewModel: ViewModel,
    currentScreen: Screen,
    startVoiceIntent: () -> Intent,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val hasLaunched = remember { mutableStateOf(false) }
    val navigationAssistant = NavigationAssistant

    val voiceLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        hasLaunched.value = false // Reset di sini setelah selesai bicara

        if (result.resultCode == RESULT_OK) {
            val spokenText = result.data
                ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                ?.firstOrNull()
                ?.lowercase(Locale.ROOT)
            viewModel.setUserCommand(spokenText ?: "")
            navigationAssistant.navigateBySpeech(
                viewModel = viewModel,
                currentScreen = currentScreen,
                stringBody = spokenText ?: "",
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        tts.speak("", TextToSpeech.QUEUE_FLUSH, null, null)
                        voiceLauncher.launch(startVoiceIntent())
                    }
                )
            }
    ) {
        content()
    }


}