package com.example.airnews.di

import com.example.airnews.Model.NewsApi
import com.example.airnews.repository.repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun getApi(): NewsApi = Retrofit.Builder().baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    @Singleton
    @Provides
    fun getRepository(): repository = repository(getApi())


}