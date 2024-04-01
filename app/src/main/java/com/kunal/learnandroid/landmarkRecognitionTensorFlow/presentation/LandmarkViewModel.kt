package com.kunal.learnandroid.landmarkRecognitionTensorFlow.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunal.learnandroid.landmarkRecognitionTensorFlow.domain.Classification
import kotlinx.coroutines.launch

class LandmarkViewModel : ViewModel() {

    private val _classifications: MutableState<List<Classification>> = mutableStateOf(emptyList())
    val classifications: State<List<Classification>> = _classifications

    fun updateResults(classifications: List<Classification>) {
        viewModelScope.launch {
            _classifications.value = classifications
        }
    }
}