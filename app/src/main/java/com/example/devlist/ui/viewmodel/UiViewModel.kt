package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class UiViewModel(): ViewModel() {
    // TODO: Implement the ViewModel

    private val _uiLivedata = MutableLiveData<DevResource>()
    val uiLiveData : LiveData<DevResource> = _uiLivedata

    // coroutines
    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui = api.getUiList("ui-design/inspiration").body()
            _uiLivedata.value = ui!!
        }
    }
}