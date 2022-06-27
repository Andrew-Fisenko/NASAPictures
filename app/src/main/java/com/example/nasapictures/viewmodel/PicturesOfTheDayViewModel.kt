package com.example.nasapictures.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.BuildConfig
import com.example.nasapictures.model.PicturesOfTheDayResponseData
import com.example.nasapictures.model.RepositoryImpl
import retrofit2.Callback

class PicturesOfTheDayViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
                                private val repositoryImpl: RepositoryImpl = RepositoryImpl()):J ViewModel() {}

)

    fun getLiveData(){

    }

    fun sendRequest(){
        repositoryImpl.getPicturesOfTheDayApi().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }

    private val callback = object : Callback<PicturesOfTheDayResponseData>{
        override fun onResponse{
            call: Call<PicturesOfTheDayResponseData>
        }

    }

    override fun onFailue(call: Callback<PicturesOfTheDayResponseData>, t: Throwable){

    }
}