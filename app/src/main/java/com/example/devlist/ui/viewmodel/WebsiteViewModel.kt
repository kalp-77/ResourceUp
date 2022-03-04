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

    private val _website2MutableLivedata = MutableLiveData<DevResource>()
    val website2LiveData: LiveData<DevResource> = _website2MutableLivedata

    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui = api.getDevList("tools-and-utilities/website-builders").body()
            val ui2 = api.getDevList("tools-and-utilities/general").body()

            _websiteMutableLivedata.value = ui!!
            _website2MutableLivedata.value = ui2!!
        }
    }
}