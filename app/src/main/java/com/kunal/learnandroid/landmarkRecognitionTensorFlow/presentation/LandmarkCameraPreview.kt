package com.kunal.learnandroid.landmarkRecognitionTensorFlow.presentation

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun LandmarkCameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val primaryColor = MaterialTheme.colorScheme.primary

    AndroidView(factory = {
        PreviewView(it).apply {
            this.controller = controller
            controller.bindToLifecycle(lifecycleOwner)
        }
    }, modifier = modifier
        .drawWithContent {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val width = canvasWidth * .9f

            drawContent()

            drawRect(Color(0x99000000))

            val offset = Offset((canvasWidth - width) / 2, canvasHeight * .3f)

            // Draws the rectangle in the middle
            drawRoundRect(
                topLeft = offset,
                size = Size(width, width),
                color = Color.Transparent,
                cornerRadius = CornerRadius(24.dp.toPx()),
                blendMode = BlendMode.SrcIn
            )

            // Draws the rectangle outline
            drawRoundRect(
                topLeft = offset,
                size = Size(width, width),
                color = primaryColor,
                cornerRadius = CornerRadius(24.dp.toPx()),
                style = Stroke(
                    width = 2.dp.toPx()
                ),
                blendMode = BlendMode.Src
            )

        }
    )
}