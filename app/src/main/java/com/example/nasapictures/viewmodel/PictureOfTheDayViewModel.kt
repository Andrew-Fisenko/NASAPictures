package com.example.nasapictures.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasapictures.BuildConfig
import com.example.nasapictures.model.PicturesOfTheDayResponseData
import com.example.nasapictures.model.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData(): MutableLiveData<AppState> {
        return liveData
    }

    fun sendRequest() {
        liveData.postValue(AppState.Loading)
        Thread {
            sleep(1000)
            repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDay(BuildConfig.NASA_API_KEY)
                .enqueue(callback)
        }.start()
    }

    fun sendRequestByDate(date: String) {
        liveData.postValue(AppState.Loading)
        Thread {
            sleep(1000)
            repositoryImpl.getPictureOfTheDayApi()
                .getPictureOfTheDayByDate(BuildConfig.NASA_API_KEY, date)
                .enqueue(callback)
        }.start()
    }

    private val callback = object : Callback<PicturesOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PicturesOfTheDayResponseData>,
            response: Response<PicturesOfTheDayResponseData>
        ) {
            if (response.isSuccessful) {
                liveData.postValue(AppState.Success(response.body()!!))
            } else {
                liveData.postValue(AppState.Error(IllegalStateException("Error!")))
            }
        }

        override fun onFailure(call: Call<PicturesOfTheDayResponseData>, t: Throwable) {
            liveData.postValue(AppState.Error(IllegalStateException("что-то пошло не так")))
        }
    }
}


