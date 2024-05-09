package com.kunal.learnandroid.fragments.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedDataViewModel : ViewModel() {
    private val _backgroundColor = MutableLiveData<String>()
    val backgroundColor: LiveData<String> get() = _backgroundColor

    fun updateBackgroundColor(color: String) {
        _backgroundColor.value = color
    }

}