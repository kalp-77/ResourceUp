package com.example.devlist.data.api

import com.example.devlist.data.model.DevResource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// api interface
interface ApiInterface {

    @GET("v1/list?")
<<<<<<< HEAD
    suspend fun getDevList(@Query("category") list:String) : Response<DevResource>
=======
    fun getApiList(@Query("category") list:String) : Call<DevResource>

    @GET("v1/list?")
    suspend fun getUiList(@Query("category") list:String) : Response<DevResource>
>>>>>>> 3def0dc (.)

}