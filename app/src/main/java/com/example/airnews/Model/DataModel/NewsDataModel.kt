package com.example.trial.Model.DataModel

data class NewsDataModel(
    val totalResults: Int,
    val status: String,
    val articles: List<Article>
)