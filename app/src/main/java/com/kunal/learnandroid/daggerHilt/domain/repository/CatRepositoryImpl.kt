package com.kunal.learnandroid.daggerHilt.domain.repository

import com.kunal.learnandroid.daggerHilt.data.entity.Cat
import com.kunal.learnandroid.daggerHilt.data.remote.ApiService
import retrofit2.Response
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CatRepository {
    override suspend fun getCats(limit: Int): Response<List<Cat>> {
        return apiService.getCats(limit)
    }
}