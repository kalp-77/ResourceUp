package com.example.devlist.data.api

import android.content.ContentValues.TAG
import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


// Api helper object
object ApiHelper {

    private const val BASE_URL = "https://api.devresourc.es/"
    private var app = Myapplication
    private const val cacheSize = (10 * 1024 * 1024).toLong()
    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"

    // retrofit instance
    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
    }

    // for caching the data from the api
    private fun okHttpClient() : OkHttpClient? {
        return httpLoggingInterceptor()?.let {
            offlineInterceptor().let { it1 ->
                networkInterceptor().let { it2 ->
                    OkHttpClient.Builder()
                        .cache(cache())
                        .connectTimeout(50,TimeUnit.SECONDS)
                        .readTimeout(50,TimeUnit.SECONDS)
                        .callTimeout(5,TimeUnit.MINUTES)
                        .addInterceptor(it)
                        .addNetworkInterceptor(it2)
                        .addInterceptor(it1)
                        .build()
                }
            }
        }
    }
    private fun cache(): Cache? {
        return Myapplication.instance?.let { Cache(it.cacheDir,  cacheSize) }
    }

    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "offline interceptor: called.")
            var request: Request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (app.hasNetwork() || !app.hasNetwork()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(30, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "network interceptor: called.")
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(30, TimeUnit.SECONDS)
                .build()
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor? {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d(TAG, "log: http log: $message")
            }
        })
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

}