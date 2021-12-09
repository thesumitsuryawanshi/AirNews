package com.example.airnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.airnews.Model.DataModel
import com.example.airnews.Model.NewsApi
import com.example.trial.Model.DataModel.NewsDataModel
import retrofit2.await

class repository(val newsApi: NewsApi) {


    private val mutableLiveData = MutableLiveData<NewsDataModel>()

    val news: LiveData<NewsDataModel>
        get() = mutableLiveData

    suspend fun getdata() {
        val news = newsApi.getNews().await()
        mutableLiveData.postValue(news)
    }

}