package com.example.nasapictures.model


import com.google.gson.annotations.SerializedName

data class PicturesOfTheDayResponseData(

    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    val title: String,
    val url: String
)