package com.example.newsarticleapp.ui

import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsarticleapp.R
import com.example.newsarticleapp.data.NewsRepository
import com.example.newsarticleapp.data.NewsViewModel
import com.example.newsarticleapp.extensions.addFragment
import com.example.newsarticleapp.network.NetworkChecker
import com.example.newsarticleapp.network.RemoteApi


@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {


    private val networkChecker by lazy {
        NetworkChecker(getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment()
    }

    private fun openFragment() {
        supportFragmentManager.addFragment(NewsListFragment(), R.id.fragment_container)
    }
}