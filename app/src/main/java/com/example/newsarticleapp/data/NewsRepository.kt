package com.example.newsarticleapp.data

import com.example.newsarticleapp.model.NewsArticleResponse
import com.example.newsarticleapp.network.NewsCallback
import com.example.newsarticleapp.network.RemoteApi

class NewsRepository(private val remoteApi: RemoteApi) {

//    suspend fun getNews() : List<News> {
//        return RemoteApi().getNews()
//    }

    interface NewsRepositoryCallback {
        fun onSuccess(newsResponse: NewsArticleResponse)
        fun onError(errorMessage: String)
    }

    fun fetchNews(callback: NewsRepositoryCallback) {
        remoteApi.getNews(object : NewsCallback {
            override fun onSuccess(newsResponse: NewsArticleResponse) {
                callback.onSuccess(newsResponse)
            }

            override fun onError(errorMessage: String) {
                callback.onError(errorMessage)
            }
        })
    }

}