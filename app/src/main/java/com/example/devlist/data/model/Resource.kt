package com.example.devlist.data.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Resource(
    @PrimaryKey(autoGenerate = true)
    val apiId: Int,
    @SerializedName("apiCategory")
    val apiCategory: String,
    @SerializedName("auth")
    val auth: String,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("cors")
    val cors: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("https")
    val https: Boolean,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("keywords")
    val keywords: List<String>,
    @SerializedName("links")
    val links: Links,
    @SerializedName("name")
    val name: String
)