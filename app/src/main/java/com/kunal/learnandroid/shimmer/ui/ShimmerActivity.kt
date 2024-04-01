package com.kunal.learnandroid.shimmer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.shimmer.ui.components.ShimmerListItem
import kotlinx.coroutines.delay

class ShimmerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {

                var isLoading by remember {
                    mutableStateOf(true)
                }
                LaunchedEffect(key1 = true) {
                    delay(10000)
                    isLoading = false
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(20) {
                        ShimmerListItem(
                            isLoading = isLoading,
                            contentAfterLoading = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(100.dp)
                                            .background(Color.White)
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(text = "This is a text to show after our shimmer")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}