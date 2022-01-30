package com.example.devlist.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Api helper object
object ApiHelper {

    private const val BASE_URL = "https://api.devresourc.es/"

    private val okHttp = OkHttpClient.Builder()
        .connectTimeout(50,TimeUnit.SECONDS)
        .readTimeout(50,TimeUnit.SECONDS)
        .callTimeout(5,TimeUnit.MINUTES)

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()
    }
}