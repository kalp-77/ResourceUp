package com.example.devlist.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Api helper object
object ApiHelper {

    val BASE_URL = "https://api.devresourc.es/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}