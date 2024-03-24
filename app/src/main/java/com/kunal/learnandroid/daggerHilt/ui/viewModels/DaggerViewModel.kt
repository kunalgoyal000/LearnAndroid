package com.kunal.learnandroid.daggerHilt.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunal.learnandroid.daggerHilt.AppConstants
import com.kunal.learnandroid.daggerHilt.data.entity.Cat
import com.kunal.learnandroid.daggerHilt.domain.repository.CatRepository
import com.kunal.learnandroid.daggerHilt.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaggerViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {

    private val _cats: MutableStateFlow<ResourceState<List<Cat>>> =
        MutableStateFlow(ResourceState.Loading())
    val cats: StateFlow<ResourceState<List<Cat>>> = _cats

    init {
        getCats(AppConstants.LIMIT)
    }

    fun getCats(limit: Int) {
        viewModelScope.launch {
            val response = catRepository.getCats(limit)

        }
    }

}