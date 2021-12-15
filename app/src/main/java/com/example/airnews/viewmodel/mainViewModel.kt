package com.example.airnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airnews.repository.repository
import com.example.trial.Model.DataModel.NewsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mainViewModel(val repository: repository) : ViewModel() {


    fun getBusinessData(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBusinessdata(category)
        }
    }

    val BNews: LiveData<NewsDataModel>
        get() = repository.Bnews


    fun getScienceData(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSciencedata(category)
        }
    }

    val SNews: LiveData<NewsDataModel>
        get() = repository.Snews


    fun getTechnologyData(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTechnologydata(category)
        }
    }

    val TNews: LiveData<NewsDataModel>
        get() = repository.Tnews


   fun getQueryData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuerydata(query)
        }
    }

    val QNews: LiveData<NewsDataModel>
        get() = repository.Qnews


}