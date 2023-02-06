package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class UiKitViewModel : ViewModel() {

    private val _uiKitLivedata = MutableLiveData<DevResource>()
    private val _tempLivedata = MutableLiveData<DevResource>()
    val uiKitLiveData : LiveData<DevResource> = _uiKitLivedata
    val tempLiveData : LiveData<DevResource> = _tempLivedata


    // coroutines
    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val pro = api.getDevList("Programming/UI Kits & Libraries").body()
            val temp = api.getDevList("Programming/Templates").body()
            _uiKitLivedata.value = pro!!
            _tempLivedata.value = temp!!
        }
    }
}