package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.ApiModel
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class PublicApiViewModel : ViewModel() {

    private val _apiMutableLivedata = MutableLiveData<ApiModel>()
    val apiLiveData: LiveData<ApiModel> = _apiMutableLivedata

    // coroutines
    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstanceApi().create(ApiInterface::class.java)
            val ui = api.getApiList().body()
            _apiMutableLivedata.value = ui!!
        }
    }
}



