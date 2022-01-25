package com.example.devlist.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Api helper object
object ApiHelper {

<<<<<<< HEAD
    private const val BASE_URL = "https://api.devresourc.es/"
=======
    val BASE_URL = "https://api.devresourc.es/"
>>>>>>> 3def0dc (.)

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}