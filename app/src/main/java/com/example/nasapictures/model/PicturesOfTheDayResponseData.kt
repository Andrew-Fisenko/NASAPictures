package com.example.nasapictures.model


import com.google.gson.annotations.SerializedName

data class PicturesOfTheDayResponseData(

    val copyright: String,

    val date: String,

    val explanation: String,
    @SerializedName("hdurl")
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)