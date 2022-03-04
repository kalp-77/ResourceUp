package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class ProgrammingViewModel : ViewModel() {

    private val _programmingLivedata = MutableLiveData<DevResource>()
    val programmingLiveData : LiveData<DevResource> = _programmingLivedata

    // coroutines
    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val pro = api.getDevList("programming/learn").body()
            _programmingLivedata.value = pro!!
        }
    }
}