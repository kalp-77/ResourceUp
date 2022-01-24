package com.example.devlist.data.model


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("Apple Podcasts")
    val applePodcasts: String,
    @SerializedName("CodePen")
    val codePen: String,
    @SerializedName("Discord")
    val discord: String,
    @SerializedName("Facebook")
    val facebook: String,
    @SerializedName("GitHub")
    val gitHub: String,
    @SerializedName("Google Podcasts")
    val googlePodcasts: String,
    @SerializedName("Gumroad")
    val gumroad: String,
    @SerializedName("Indie Hackers")
    val indieHackers: String,
    @SerializedName("Instagram")
    val instagram: String,
    @SerializedName("LinkedIn")
    val linkedIn: String,
    @SerializedName("Product Hunt")
    val productHunt: String,
    @SerializedName("Slack")
    val slack: String,
    @SerializedName("Spotify")
    val spotify: String,
    @SerializedName("Twitch")
    val twitch: String,
    @SerializedName("Twitter")
    val twitter: String,
    @SerializedName("Website")
    val website: String,
    @SerializedName("YouTube")
    val youTube: String
)