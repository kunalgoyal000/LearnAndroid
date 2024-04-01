package com.kunal.learnandroid.landmarkRecognitionTensorFlow.presentation

import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.kunal.learnandroid.landmarkRecognitionTensorFlow.data.TFLiteLandmarkClassifier
import com.kunal.learnandroid.landmarkRecognitionTensorFlow.domain.Classification

@Composable
fun LandmarkScreen(viewModel: LandmarkViewModel) {

    val context = LocalContext.current

    val analyzer = remember {
        LandmarkImageAnalyzer(
            classifier = TFLiteLandmarkClassifier(
                context = context
            ),
            onResults = {
                viewModel.updateResults(it)
            }
        )
    }

    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context), analyzer
            )
        }
    }

    val state by viewModel.classifications
    LandmarkScreenContent(state, controller)
}

@Composable
fun LandmarkScreenContent(
    classifications: List<Classification>,
    controller: LifecycleCameraController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LandmarkCameraPreview(controller, Modifier.fillMaxSize())

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            classifications.forEach {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
