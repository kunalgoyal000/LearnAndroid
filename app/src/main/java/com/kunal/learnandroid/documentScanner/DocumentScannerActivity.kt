package com.kunal.learnandroid.documentScanner

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.kunal.learnandroid.ui.theme.LearnAndroidTheme
import java.io.File
import java.io.FileOutputStream

class DocumentScannerActivity : AppCompatActivity() {

    val options = GmsDocumentScannerOptions.Builder().apply {
        setScannerMode(SCANNER_MODE_FULL)
        setGalleryImportAllowed(true)
        setPageLimit(5)
        setResultFormats(RESULT_FORMAT_JPEG, RESULT_FORMAT_PDF)
    }.build()

    private val scanner = GmsDocumentScanning.getClient(options)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                    var imageUris by remember {
                        mutableStateOf<List<Uri>>(emptyList())
                    }
                    val scannerLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = {
                            if(it.resultCode == RESULT_OK){
                                val result = GmsDocumentScanningResult.fromActivityResultIntent(it.data)
                                imageUris = result?.pages?.map { it.imageUri} ?: emptyList()

                                result?.pdf?.let { pdf ->
                                    val fos = FileOutputStream(File(filesDir,"Scan.pdf"))
                                    contentResolver.openInputStream(pdf.uri)?.use {
                                        it.copyTo(fos)
                                    }
                                }
                            }
                        }
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        imageUris.forEach { uri ->
                            AsyncImage(
                                model = uri,
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Button(onClick = {
                            scanner.getStartScanIntent(this@DocumentScannerActivity)
                                .addOnSuccessListener {
                                    scannerLauncher.launch(
                                        IntentSenderRequest.Builder(it).build()
                                    )
                                }
                                .addOnFailureListener{
                                    Toast.makeText(
                                        applicationContext,
                                        it.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                        }) {
                         Text(text = "Scan PDF")
                        }
                    }
                }
            }
        }
    }