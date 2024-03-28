package com.example.newsarticleapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsarticleapp.databinding.ItemNewsBinding
import com.example.newsarticleapp.model.News

class NewsItemViewHolder(private val binding: ItemNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: News?, onClick : ((item: News) -> Unit)?) {
        item?.let{
            binding.apply {
                title.text = item.title
                author.text = item.author
                viewmore.setOnClickListener {
                    onClick?.invoke(item)
                }
            }
            Glide.with(binding.root)
                .load(item.urlToImage)
                .into(binding.userImage)
        }
    }
}