package com.ahmednmahran.newsapp.model

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("articles")
    var articles: List<Article>? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    var totalResults: Long? = null

)
