package com.ahmednmahran.newsapp.repository

import android.util.Log
import retrofit2.Response
import java.io.IOException

/**
 * Created by Ahmed Nabil on 2019-07-10.
 * A base repository class to handle the safe API calls.
 */
open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call : suspend()-> Response<T>, error : String) :  T?{
        val result = newsApiOutput(call, error)
        var output : T? = null
        when(result){
            is Output.Success ->
                output = result.output
            is Output.Error -> Log.e("Error", "The $error and the ${result.exception}")
        }
        return output

    }
    private suspend fun<T : Any> newsApiOutput(call: suspend()-> Response<T>, error: String) : Output<T>{
        val response = call.invoke()
        return if (response.isSuccessful)
            Output.Success(response.body()!!)
        else
            Output.Error(IOException("Sorry .. Something went wrong due to  $error"))
    }
}