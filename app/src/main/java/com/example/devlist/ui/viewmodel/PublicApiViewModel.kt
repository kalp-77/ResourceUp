package com.example.devlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import kotlinx.coroutines.launch

class PublicApiViewModel : ViewModel() {

    private val _apiMutableLivedata = MutableLiveData<DevResource>()
    val apiLiveData: LiveData<DevResource> = _apiMutableLivedata

    // coroutines
    init {
        viewModelScope.launch {
            val api = ApiHelper.getInstance().create(ApiInterface::class.java)
            val ui = api.getDevList("tools-and-utilities/public-apis").body()

                //apiDatabase.apiDao().addApi(ui.resources.toList())
               // apiDatabase.apiDao().addApi(ui.resources)
                _apiMutableLivedata.value = ui!!
        }
    }
}



