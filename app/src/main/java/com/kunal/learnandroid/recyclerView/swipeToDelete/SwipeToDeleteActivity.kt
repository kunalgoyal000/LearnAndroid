@file:OptIn(ExperimentalMaterial3Api::class)

package com.kunal.learnandroid.recyclerView.swipeToDelete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme

class SwipeToDeleteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val programmingLanguages = remember {
                        mutableStateListOf(
                            "Kotlin",
                            "Java",
                            "C++",
                            "JavaScript",
                            "Python"
                        )
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = programmingLanguages,
                            key = { it }
                        ) { language ->
                            SwipeToDeleteContainer(
                                item = language,
                                onDelete = {
                                    programmingLanguages -= language
                                }
                            ) { language ->
                                Text(
                                    text = language,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.background)
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}