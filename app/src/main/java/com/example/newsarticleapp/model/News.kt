package com.example.newsarticleapp.model

import com.google.gson.annotations.SerializedName

data class News (
    @SerializedName("author")
    val author : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("urlToImage")
    val urlToImage : String,
    @SerializedName("publishedAt")
    val publishedAt : String,
    @SerializedName("content")
    val content : String
) {
    constructor() : this("","","","","")
}
