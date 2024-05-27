package com.example.lab5.data.api

import com.example.lab5.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TestApi {

    @GET("b/65f6de3bdc74654018b42b35/")
    fun getLocalItems(@Header("X-MASTER-KEY") secretKey: String): Call<ApiResponse>

}
