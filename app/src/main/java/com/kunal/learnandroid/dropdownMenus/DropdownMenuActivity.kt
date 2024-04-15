package com.kunal.learnandroid.dropdownMenus

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme

class DropdownMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(
                        listOf(
                            "Kunal",
                            "Tarun",
                            "Sanjay",
                            "Seema",
                            "Rohit",
                            "Paras"
                        )
                    ) { name ->
                        PersonItem(
                            personName = name,
                            dropdownItems = listOf(
                                DropdownItem("Item 1"),
                                DropdownItem("Item 2"),
                                DropdownItem("Item 3")
                            ),
                            onItemClick = {
                                Toast.makeText(
                                    applicationContext,
                                    it.text,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                    }
                }
            }
        }
    }
}