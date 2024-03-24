package com.kunal.learnandroid.daggerHilt.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.kunal.learnandroid.daggerHilt.ui.components.CatList
import com.kunal.learnandroid.daggerHilt.ui.components.Loader
import com.kunal.learnandroid.daggerHilt.ui.viewModels.DaggerViewModel
import com.kunal.learnandroid.daggerHilt.utils.ResourceState

@Composable
fun CatGalleryScreen(viewModel: DaggerViewModel = hiltViewModel()) {

    val catsRes by viewModel.cats.collectAsState()

    when (catsRes) {
        is ResourceState.Loading -> {
            Loader()
        }

        is ResourceState.Success -> {
            val response = (catsRes as ResourceState.Success).data
            CatList(response)
        }

        is ResourceState.Error -> {
            val error = (catsRes as ResourceState.Error)
        }
    }

}