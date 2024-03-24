package com.kunal.learnandroid.daggerHilt.data.remote

import com.kunal.learnandroid.daggerHilt.data.entity.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/images/search")
    suspend fun getCats(@Query("limit") limit: Int): Response<List<Cat>>
}