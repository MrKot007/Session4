package com.example.session4try1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface API {
    @GET("workSpace/plan")
    fun getPlan(@Query("date") date: String? = null,
                @Query("direction") direction: String? = null,
                @Header("idUser") idUser: String = Info.idUser) : Call<List<ModelTask>>
    @GET("workSpace/delayLesson")
    fun getExpired(@Header("idUser") idUser: String = Info.idUser) : Call<List<ModelTask>>
}