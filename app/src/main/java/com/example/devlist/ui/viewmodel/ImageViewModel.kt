package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    private val _imageLivedata = MutableLiveData<DevResource>()
    val imageLiveData : LiveData<DevResource> = _imageLivedata

    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui = api.getDevList("Assets/Images").body()
            _imageLivedata.value = ui!!
        }
    }
}