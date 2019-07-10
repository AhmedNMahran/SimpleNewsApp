package com.ahmednmahran.newsapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmednmahran.newsapp.repository.NewsApiService
import com.ahmednmahran.newsapp.repository.NewsRepository
import com.ahmednmahran.newsapp.model.Article
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ahmed Nabil on 2019-07-10.
 */

class NewsViewModel : ViewModel(){
    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)
    //initialize news repo
    private val newsRepository : NewsRepository = NewsRepository(NewsApiService.newsApi)
    //live data that will be populated as news updates
    val newsLiveData = MutableLiveData<MutableList<Article>>()
    fun getLatestNews() {
        ///launch the coroutine scope
        scope.launch {
            //get latest news from news repo
            val latestNews = newsRepository.getLatestNews()
            //post the value inside live data
            newsLiveData.postValue(latestNews)

        }
    }
    fun cancelRequests() = coroutineContext.cancel()
}