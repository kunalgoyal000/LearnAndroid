package com.kunal.learnandroid.lazyColumn.nestedScrolling.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.lazyColumn.nestedScrolling.ui.components.NestedScrolling

class NestedScrollingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                NestedScrolling()
            }
        }
    }
}