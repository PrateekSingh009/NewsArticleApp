package com.example.newsarticleapp.network

import android.util.Log
import com.example.newsarticleapp.model.News
import com.example.newsarticleapp.model.NewsArticleResponse
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RemoteApi {

    private val BASE_URL = "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"

    val TAG = "RemoteApi"

    fun getNews(callback: NewsCallback)  {
        Thread {

            val connection = URL(BASE_URL).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.doInput = true

            try {
                val reader = InputStreamReader(connection.inputStream)

                reader.use { input ->

                    val response = StringBuilder()
                    val bufferedReader = BufferedReader(input)


                    bufferedReader.forEachLine {
                        response.append(it.trim())
                    }

                    Log.d(TAG, "In_Success ${response.toString()}")

                    val gson = Gson()
                    var mNewsResponse : NewsArticleResponse = gson.fromJson(response.toString(), NewsArticleResponse::class.java)

                    Log.i(TAG, "Author ${mNewsResponse.articles}")
                    callback.onSuccess(mNewsResponse)
                }

            } catch (e: Exception) {
                Log.d(TAG, "In_Error ${e.localizedMessage}")
                callback.onError(e.localizedMessage ?: "Unknown error")
            }finally {
                connection.disconnect()
            }
        }.start()

    }
}