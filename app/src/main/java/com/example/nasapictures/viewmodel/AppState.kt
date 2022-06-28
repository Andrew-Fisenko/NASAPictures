package com.example.nasapictures.viewmodel

import com.example.nasapictures.model.PicturesOfTheDayResponseData

sealed class AppState {
    object Loading : AppState()
    data class Success(val pictureOfTheDayResponseData: PicturesOfTheDayResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
}
