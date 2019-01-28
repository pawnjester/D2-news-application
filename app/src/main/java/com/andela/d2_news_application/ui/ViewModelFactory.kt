package com.andela.d2_news_application.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.viewModel.SharedViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val repository: ResultRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}