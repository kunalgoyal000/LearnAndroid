package com.kunal.learnandroid.material3

import android.content.Intent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.material3.buttons.ButtonActivity
import com.kunal.learnandroid.material3.textFields.TextFieldActivity

class Material3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(top = 50.dp, bottom = 50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@Material3Activity,
                                ButtonActivity::class.java
                            )
                        )
                    }) {
                        Text(text = "Buttons")
                    }
                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@Material3Activity,
                                TextFieldActivity::class.java
                            )
                        )
                    }) {
                        Text(text = "TextFields")
                    }
                }
            }
        }
    }
}