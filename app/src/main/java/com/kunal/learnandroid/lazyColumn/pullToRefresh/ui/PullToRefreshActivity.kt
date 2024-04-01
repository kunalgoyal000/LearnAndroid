package com.kunal.learnandroid.lazyColumn.pullToRefresh.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.lazyColumn.pullToRefresh.ui.components.PullToRefreshLazyColumn
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PullToRefreshActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LearnAndroidTheme {
                val items = remember {
                    (1..100).map { "Item $it" }
                }

                var isRefreshing by remember {
                    mutableStateOf(false)
                }

                val scope = rememberCoroutineScope()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    PullToRefreshLazyColumn(
                        items = items,
                        content = { itemTitle ->
                            Text(
                                text = itemTitle,
                                modifier = Modifier
                                    .padding(16.dp)
                            )
                        },
                        isRefreshing = isRefreshing,
                        onRefresh = {
                            scope.launch {
                                isRefreshing = true
                                delay(3000L)
                                isRefreshing = false
                            }
                        }
                    )

                    Button(
                        onClick = {
                            isRefreshing = true
                        },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        Text(text = "Refresh")
                    }
                }
            }
        }
    }
}