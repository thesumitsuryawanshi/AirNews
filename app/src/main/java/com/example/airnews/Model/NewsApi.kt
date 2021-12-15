package com.example.airnews.Model

import com.example.trial.Model.DataModel.NewsDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

// https://newsapi.org/v2/top-headlines?country=in&apiKey=11c6dba5e88744338808d830416b0b8f
//    https://newsapi.org/v2/everything?q=India&apiKey=11c6dba5e88744338808d830416b0b8f



    @GET("/v2/top-headlines")
     fun getNews(
        @Query("category") myCategory :String
     ): Call<NewsDataModel>

    @GET("/v2/everything")
     fun getQueryNews(
        @Query("q") query :String
     ): Call<NewsDataModel>

}
