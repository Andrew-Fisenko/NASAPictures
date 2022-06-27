package com.example.nasapictures.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesOfTheDayAPI {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey:String): Call<PicturesOfTheDayAPI>
}