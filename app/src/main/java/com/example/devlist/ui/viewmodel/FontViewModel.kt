package com.example.devlist.ui.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class FontViewModel : ViewModel() {

    private val _fontLivedata = MutableLiveData<DevResource>()
    val fontLiveData : LiveData<DevResource> = _fontLivedata

    // coroutines
    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui = api.getDevList("Assets/Fonts").body()
            _fontLivedata.value = ui!!
        }
    }
}