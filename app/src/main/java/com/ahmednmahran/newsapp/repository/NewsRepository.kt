package com.ahmednmahran.newsapp.repository

import com.ahmednmahran.newsapp.model.Article

/**
 * Created by Ahmed Nabil on 2019-07-10.
 */
class NewsRepository(private val apiInterface: NewsApiInterface) : BaseRepository() {
    //get latest news using safe api call
    suspend fun getLatestNews() :  MutableList<Article>?{
        return safeApiCall(
            //await the result of deferred type
            call = {apiInterface.fetchLatestNewsAsync("USA", "publishedAt").await()},
            error = "Error fetching news"
            //convert to mutable list
        )?.articles?.toMutableList()
    }
}