package com.example.app2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("workSpace/plan")
    fun getPlan(@Query("date") date: String? = null, @Query("courseId") courseId: Int? = null, @Query("direction") direction: String? = null) : Call<ModelTask>
    @GET("workSpace/delayLessons")
    fun getDelayed() : Call<ModelTask>
}