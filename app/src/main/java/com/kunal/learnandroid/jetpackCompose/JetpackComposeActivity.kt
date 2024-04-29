package com.kunal.learnandroid.jetpackCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.jetpackCompose.animations.JetpackAnimationScreen
import com.kunal.learnandroid.jetpackCompose.image.JetpackImageCardScreen
import com.kunal.learnandroid.jetpackCompose.state.JetpackStateScreen
import com.kunal.learnandroid.jetpackCompose.text.JetpackTextScreen

class JetpackComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "jetpack") {
                    composable("jetpack") {
                        JetpackComposeScreen(navController)
                    }
                    composable("jetpackText") {
                        JetpackTextScreen()
                    }
                    composable("jetpackImageCard") {
                        JetpackImageCardScreen()
                    }
                    composable("jetpackState") {
                        JetpackStateScreen()
                    }
                    composable("jetpackAnimation") {
                        JetpackAnimationScreen()
                    }
                }
            }
        }
    }
}

@Composable
private fun JetpackComposeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 50.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            navController.navigate("jetpackText")
        }) {
            Text(text = "Text")
        }
        Button(onClick = {
            navController.navigate("jetpackImageCard")
        }) {
            Text(text = "Image Card")
        }
        Button(onClick = {
            navController.navigate("jetpackState")
        }) {
            Text(text = "State")
        }
        Button(onClick = {
            navController.navigate("jetpackAnimation")
        }) {
            Text(text = "Animations")
        }
    }
}