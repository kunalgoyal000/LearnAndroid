package com.kunal.learnandroid.material3.textFields

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme

class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {

            }
        }
    }
}