package com.memasakataudimasak.buddylearn.ui.screen.landingpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.memasakataudimasak.buddylearn.R
import com.memasakataudimasak.buddylearn.data.Screen

@Composable
fun LandingPage(navController: NavController) {
    Row (
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.landing_title),
            contentDescription = "Landing Title",
            modifier = Modifier.height(62.dp).width(209.dp)
        )
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.landing_text),
            contentDescription = "Landing Title",
            modifier = Modifier.height(168.dp).width(374.dp)
        )

        Spacer(modifier = Modifier.height(85.dp))

        Image(
            painter = painterResource(id = R.drawable.landing_image),
            contentDescription = "Landing Image",
            modifier = Modifier.height(170.dp).width(200.dp)
        )

        Button(
            onClick = {
                navController.navigate(Screen.Signup.name)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFADF9B2),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .width(300.dp)
                .height(54.dp),
            shape = RoundedCornerShape(24)
        ) {
            Text(text = "Sign up with E-mail")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                color = Color.LightGray
            )

            Text(
                text = "or",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Gray
            )

            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                color = Color.LightGray
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? ")
            TextButton(onClick = {
                navController.navigate(Screen.Login.name)
            }) {
                Text(text = "Log in")
            }
        }
    }
}