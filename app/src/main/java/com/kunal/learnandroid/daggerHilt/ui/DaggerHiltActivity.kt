package com.kunal.learnandroid.daggerHilt.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.daggerHilt.ui.screens.CatGalleryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DaggerHiltActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                CatGalleryScreen()
            }
        }
    }
}