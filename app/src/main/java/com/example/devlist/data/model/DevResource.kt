package com.example.devlist.data.model


import com.google.gson.annotations.SerializedName

data class DevResource(
    val count: Int,
    val data: List<Resource>,
    val resources: List<Resource>,
    val entries: List<Resource>
)