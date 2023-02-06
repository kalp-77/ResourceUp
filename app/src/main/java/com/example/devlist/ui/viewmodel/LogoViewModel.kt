package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class LogoViewModel : ViewModel() {
    private val _logoLivedata = MutableLiveData<DevResource>()
    private val _logoLivedata2 = MutableLiveData<DevResource>()

    val logoLiveData : LiveData<DevResource> = _logoLivedata
    val logoLiveData2 : LiveData<DevResource> = _logoLivedata2

    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui2 = api.getDevList("Assets/Illustrations").body()
            val ui = api.getDevList("Assets/Logos").body()
            _logoLivedata.value = ui!!
            _logoLivedata2.value = ui2!!

        }
    }
}
