package com.ahmednmahran.newsapp.repository

import com.ahmednmahran.newsapp.model.News
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmed Nabil on 2019-07-10.
 */
interface  NewsApiInterface{
    //fetches latest news with the required query params
    @GET("v2/everything")
    fun fetchLatestNewsAsync(@Query("q") query: String,
                             @Query("sortBy") sortBy : String) : Deferred<Response<News>>
}