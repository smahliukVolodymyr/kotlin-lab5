package com.example.lab5.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiHelper {
    var retrofit: Retrofit?=null

    fun init(): RetrofitApiHelper {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this
    }


}

//class RetrofitApiHelper {
//    var api: Retrofit?=null
//
//    fun init() {
//
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.jsonbin.io/v3/")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        api = retrofit.create(TestApi::class.java)
//    }
//}
