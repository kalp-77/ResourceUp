package com.example.devlist.data.model


import com.google.gson.annotations.SerializedName

data class DevResource(
    @SerializedName("count")
    val count: Int,
    @SerializedName("resources")
    val resources: List<Resource>
)