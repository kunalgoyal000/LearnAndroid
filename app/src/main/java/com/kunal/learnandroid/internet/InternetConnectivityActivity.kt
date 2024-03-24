package com.kunal.learnandroid.internet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme

class InternetConnectivityActivity : AppCompatActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            LearnAndroidTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Network Status: $status")
                }
            }
        }
    }

}
