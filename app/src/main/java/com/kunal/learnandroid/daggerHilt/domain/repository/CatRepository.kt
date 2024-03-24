package com.kunal.learnandroid.daggerHilt.domain.repository

import com.kunal.learnandroid.daggerHilt.data.entity.Cat
import retrofit2.Response

interface CatRepository {
    suspend fun getCats(limit: Int): Response<List<Cat>>
}