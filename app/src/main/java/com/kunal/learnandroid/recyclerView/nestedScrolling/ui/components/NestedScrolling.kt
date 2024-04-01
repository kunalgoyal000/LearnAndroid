package com.kunal.learnandroid.recyclerView.nestedScrolling.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NestedScrolling() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            SubList1()
        }
        subList2()
    }
}

@Composable
fun SubList1() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
        }
    }
}

fun LazyListScope.subList2() {
    items(10) {
        Text(
            text = "Hello World",
            modifier = Modifier.padding(32.dp)
        )
    }
}
