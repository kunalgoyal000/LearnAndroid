package com.kunal.learnandroid.landmarkRecognitionTensorFlow

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.landmarkRecognitionTensorFlow.presentation.LandmarkScreen
import com.kunal.learnandroid.landmarkRecognitionTensorFlow.presentation.LandmarkViewModel

class LandmarkRecognitionTensorFlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val landmarkViewModel: LandmarkViewModel by viewModels()

        if (!hasCameraPermission()) {
            ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        }

        setContent {
            LearnAndroidTheme {
                LandmarkScreen(viewModel = landmarkViewModel)
            }
        }
    }

    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}