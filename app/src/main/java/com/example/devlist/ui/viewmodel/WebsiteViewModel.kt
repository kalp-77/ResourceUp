package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class WebsiteViewModel : ViewModel() {
    private val _websiteMutableLivedata = MutableLiveData<DevResource>()
    val websiteLiveData: LiveData<DevResource> = _websiteMutableLivedata

    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui = api.getDevList("tools-and-utilities/website-builders").body()
            _websiteMutableLivedata.value = ui!!
        }
    }
}