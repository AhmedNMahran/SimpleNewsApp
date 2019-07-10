package com.ahmednmahran.newsapp.repository

/**
 * Created by Ahmed Nabil on 2019-07-10.
 * A sealed class to handle network response. for both cases success or failure
 */

sealed class Output<out T : Any>{
    data class Success<out T : Any>(val output : T) : Output<T>()
    data class Error(val exception: Exception)  : Output<Nothing>()
}