package com.example.devlist.data.api
import android.app.Application
import android.net.ConnectivityManager

class Myapplication : Application() {
    companion object {
        var instance: Myapplication? = null

        fun hasNetwork(): Boolean {
            return instance!!.isNetworkConnected()
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    // check if network is connected
    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

}