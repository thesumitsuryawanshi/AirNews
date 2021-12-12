package com.example.trial.Model.DataModel

data class
Article(
    var author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    var title: String,
    var url: String,
    var urlToImage: String
)