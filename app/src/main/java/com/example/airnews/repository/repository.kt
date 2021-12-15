package com.example.airnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.airnews.Model.NewsApi
import com.example.trial.Model.DataModel.NewsDataModel
import retrofit2.await

class repository(val newsApi: NewsApi) {


    private val  mutableLiveBData = MutableLiveData<NewsDataModel>()
    private val  mutableLiveTData = MutableLiveData<NewsDataModel>()
    private val  mutableLiveSData = MutableLiveData<NewsDataModel>()
    private val  mutableLiveQData = MutableLiveData<NewsDataModel>()



    val Bnews: LiveData<NewsDataModel>
        get() = mutableLiveBData

    suspend fun getBusinessdata(category: String) {
        val news = newsApi.getNews(category).await()
        mutableLiveBData.postValue(news)
    }


    val Tnews: LiveData<NewsDataModel>
        get() = mutableLiveTData

    suspend fun getTechnologydata(category: String) {
        val news = newsApi.getNews(category).await()
        mutableLiveTData.postValue(news)
    }


    val Snews: LiveData<NewsDataModel>
        get() = mutableLiveSData

    suspend fun getSciencedata(category: String) {
        val news = newsApi.getNews(category).await()
        mutableLiveSData.postValue(news)
    }

   val Qnews: LiveData<NewsDataModel>
        get() = mutableLiveQData

    suspend fun getQuerydata(query: String) {
        val news = newsApi.getQueryNews(query).await()
        mutableLiveQData.postValue(news)
    }

}