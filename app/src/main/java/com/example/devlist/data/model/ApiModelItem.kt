package com.example.devlist.data.model

data class ApiModelItem(
    val _id: String,
    val count: Int,
    val entries: List<Entry>
)