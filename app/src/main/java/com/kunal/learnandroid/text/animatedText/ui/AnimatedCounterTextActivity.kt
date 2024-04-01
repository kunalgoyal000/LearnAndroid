package com.kunal.learnandroid.text.animatedText.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.text.animatedText.ui.components.AnimatedCounter

class AnimatedCounterTextActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var count by remember {
                        mutableIntStateOf(0)
                    }
                    AnimatedCounter(
                        count = count,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Button(onClick = { count++ }) {
                        Text(text = "Increment")
                    }
                    Button(onClick = { count-- }) {
                        Text(text = "Decrement")
                    }
                }
            }
        }
    }
}