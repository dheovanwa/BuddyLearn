package com.memasakataudimasak.buddylearn.ui.screen.login

import android.widget.Toast
import com.memasakataudimasak.buddylearn.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.memasakataudimasak.buddylearn.AuthState
import com.memasakataudimasak.buddylearn.AuthViewModel
import com.memasakataudimasak.buddylearn.data.Screen
import com.memasakataudimasak.buddylearn.ui.theme.BuddyLearnTheme
import okhttp3.internal.notifyAll


@Composable
fun Login(navController: NavController, authViewModel: AuthViewModel) {
    var showPassword by remember { mutableStateOf(value = false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Authenticated -> navController.navigate(Screen.Home.name)
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified)) {
        Image(
            painter = painterResource(id = R.drawable.back_button),
            contentDescription = "Back button",
            modifier = Modifier.size(16.dp))
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 160.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter your account details", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = email, onValueChange = {email = it}, modifier = Modifier.width(300.dp), label = {
            Text(text = "Email")
        })

        Spacer(modifier = Modifier.height(43.5.dp))

        OutlinedTextField(value = password, onValueChange = { password = it }, modifier = Modifier.width(300.dp),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
            label = {
                Text(text = "Password")
            })

        Spacer(modifier = Modifier.height(39.dp))

        Button(
            onClick = {
                authViewModel.login(email, password)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFADF9B2),
                contentColor = Color.Black
            ),
            enabled = authState.value != AuthState.Loading,
            modifier = Modifier
                .width(300.dp)
                .height(54.dp),
            shape = RoundedCornerShape(24)
        ) {
            Text("Login", fontSize = 24.sp  )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate(Screen.Signup.name)
        }) {
            Text(text = "Don't have an account?")
        }
    }
}