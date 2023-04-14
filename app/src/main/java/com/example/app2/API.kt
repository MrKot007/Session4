package com.example.app2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface API {
    @GET("workSpace/plan")
    fun getPlan(@Query("date") date: String? = null,
                @Query("direction") direction: String? = null,
                @Header("isUser") idUser: String = Info.idUser
    ) : Call<List<ModelTask>>
    @GET("workSpace/delayLessons")
    fun getDelayed(@Header("idUser") idUser: String = Info.idUser) : Call<List<ModelTask>>
}