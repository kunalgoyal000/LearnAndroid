package com.kunal.learnandroid.notifications.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.notifications.Counter
import com.kunal.learnandroid.notifications.CounterNotificationService

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                val service = CounterNotificationService(applicationContext)
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = {
                            service.showNotification(Counter.value)
                        },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = "Show counter notification")
                    }
                }
            }
        }
    }
}