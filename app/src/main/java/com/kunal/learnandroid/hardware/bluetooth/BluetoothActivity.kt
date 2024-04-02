package com.kunal.learnandroid.hardware.bluetooth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme

class BluetoothActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {

            }
        }
    }
}