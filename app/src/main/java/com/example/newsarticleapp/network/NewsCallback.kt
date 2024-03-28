package com.example.newsarticleapp.network

import com.example.newsarticleapp.model.NewsArticleResponse

interface NewsCallback {
    fun onSuccess(newsResponse: NewsArticleResponse)
    fun onError(errorMessage: String)
}