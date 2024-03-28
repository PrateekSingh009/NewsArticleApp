package com.example.newsarticleapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsarticleapp.model.News
import com.example.newsarticleapp.model.NewsArticleResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val _newsListLiveData = MutableLiveData<List<News>>()
    val newsListLiveData: LiveData<List<News>> = _newsListLiveData

//    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
//
//    fun getListFromApi() {
//        coroutineScope.launch {
//            _newsListLiveData.postValue(repository.getNews())
//        }
//    }

    fun fetchNews() {
        repository.fetchNews(object : NewsRepository.NewsRepositoryCallback {
            override fun onSuccess(newsResponse: NewsArticleResponse) {
                _newsListLiveData.postValue(newsResponse.articles)
                Log.i("Article",newsResponse.articles.toString())
            }

            override fun onError(errorMessage: String) {
                Log.e("Error",errorMessage)
            }
        })
    }

}