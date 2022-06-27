package com.example.nasapictures.viewmodel

import com.example.nasapictures.model.PicturesOfTheDayResponseData

sealed class AppState {
    data class Success(val PictureOfTheDayResponceData : PicturesOfTheDayResponseData):AppState()
    data class Error(val error : Throwable):AppState()
    object Loading :
}
