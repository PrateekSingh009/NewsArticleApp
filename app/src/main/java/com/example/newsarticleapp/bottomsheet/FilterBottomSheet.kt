package com.example.newsarticleapp.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.newsarticleapp.databinding.FilterBottomSheetBinding
import com.example.newsarticleapp.model.News
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheet(
    val newsItem : News
) : BottomSheetDialogFragment() {

    private lateinit var _binding : FilterBottomSheetBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            title.text = newsItem.title
            content.text = newsItem.content
            published.text = newsItem.publishedAt
            author.text = newsItem.author
            Glide.with(binding.root)
                .load(newsItem.urlToImage)
                .into(userImage)
        }
    }
}