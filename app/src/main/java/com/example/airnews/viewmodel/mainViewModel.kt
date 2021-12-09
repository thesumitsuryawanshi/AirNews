package com.example.airnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airnews.Model.DataModel
import com.example.airnews.repository.repository
import com.example.trial.Model.DataModel.NewsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mainViewModel(val repository: repository) : ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getdata()
        }
    }

    val News: LiveData<NewsDataModel>
        get() = repository.news


}