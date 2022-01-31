package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class BoardsViewModel : ViewModel() {
    private val _boardsLivedata = MutableLiveData<DevResource>()
    val boardsLiveData : LiveData<DevResource> = _boardsLivedata
    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui = api.getDevList("jobs/boards").body()
            _boardsLivedata.value = ui!!
        }
    }
}