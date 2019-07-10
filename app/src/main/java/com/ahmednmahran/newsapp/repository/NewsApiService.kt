package com.ahmednmahran.newsapp.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ahmed Nabil on 2019-07-10.
 * The API client helps in building the URL. We using Okhttp to create HTTP requests,
 * adding an interceptor that will allow us to add the API key as a query param to every request
 * and creating our HTTP request and handler using retrofit
 */

object NewsApiService {
    //creating a Network Interceptor to add api_key in all the request as authInterceptor
    private val interceptor = Interceptor { chain ->
        val API_KEY = "ADD_YOUR_KEY_HERE" // get your API_KEY from newsapi.org
        //here we add API key so we don't need to add it to each request method we create.
        val url = chain.request().url().newBuilder().addQueryParameter("apiKey", API_KEY).build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }
    // we are creating a networking client using OkHttp and add our authInterceptor.
    // we user Custom made #UnsafeClient to avoid SSl exceptions as the NewsApi.org certificates is not accepted by all android devices or versions
//    private val apiClient = UnsafeClient().unsafeOkHttpClient.addInterceptor(interceptor).build()

    private fun getRetrofit(): Retrofit {
        val apiClient = UnsafeClient().unsafeOkHttpClient.addInterceptor(interceptor).build()
        return Retrofit.Builder().client(apiClient)
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val newsApi: NewsApiInterface = getRetrofit().create(NewsApiInterface::class.java)

}