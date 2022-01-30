package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class ResumeViewModel : ViewModel() {
    private val _resumeMutableLivedata = MutableLiveData<DevResource>()
    val resumeLiveData: LiveData<DevResource> = _resumeMutableLivedata

    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui = api.getDevList("jobs/resume-builders").body()
            _resumeMutableLivedata.value = ui!!
        }
    }
}