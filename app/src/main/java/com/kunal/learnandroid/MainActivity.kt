package com.kunal.learnandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kunal.learnandroid.appShortcut.AppShortcutActivity
import com.kunal.learnandroid.biometric.BiometricActivity
import com.kunal.learnandroid.documentScanner.DocumentScannerActivity
import com.kunal.learnandroid.ui.theme.LearnAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LearnAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, BiometricActivity::class.java))
                        }) {
                            Text(text = "Biometric")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    DocumentScannerActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Document Scanner")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    AppShortcutActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "App Shortcut")
                        }
                    }
                }
            }
        }
    }
}