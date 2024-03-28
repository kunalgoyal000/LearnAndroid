package com.kunal.learnandroid.lazyColumn.nestedScrolling.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.lazyColumn.nestedScrolling.ui.components.NestedScrolling

class NestedScrollingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                NestedScrolling()
            }
        }
    }
}