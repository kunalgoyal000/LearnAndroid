package com.kunal.learnandroid.paging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.paging.presentation.BeerScreen
import com.kunal.learnandroid.paging.presentation.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                val viewModel = hiltViewModel<BeerViewModel>()
                val beers = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                BeerScreen(beers = beers)
            }
        }
    }
}