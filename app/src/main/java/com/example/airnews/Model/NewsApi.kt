package com.example.airnews.Model

import com.example.trial.Model.DataModel.NewsDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface NewsApi {

    @GET("/v2/top-headlines?category=business&apiKey=11c6dba5e88744338808d830416b0b8f")
     fun getNews(): Call<NewsDataModel>
}
