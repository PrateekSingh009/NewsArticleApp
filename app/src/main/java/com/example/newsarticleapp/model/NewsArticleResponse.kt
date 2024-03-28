package com.example.newsarticleapp.model

import com.google.gson.annotations.SerializedName

data class NewsArticleResponse(
    @SerializedName("status")
    val status : String,
    @SerializedName("articles")
    val articles : List<News>
)
