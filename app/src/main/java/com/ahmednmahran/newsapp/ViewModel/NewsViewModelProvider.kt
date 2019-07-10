package com.ahmednmahran.newsapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Ahmed Nabil on 2019-07-10.
 */
@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel() as T
    }
}