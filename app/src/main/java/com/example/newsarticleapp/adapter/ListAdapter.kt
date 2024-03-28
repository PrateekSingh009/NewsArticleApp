package com.example.newsarticleapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsarticleapp.databinding.ItemNewsBinding
import com.example.newsarticleapp.model.News
import com.example.newsarticleapp.viewholders.NewsItemViewHolder

class ListAdapter(
    private val list: List<News>,
    private val onClick: ((item: News) -> Unit)?,
) : RecyclerView.Adapter<NewsItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(list[position] as News?, onClick)
    }

}