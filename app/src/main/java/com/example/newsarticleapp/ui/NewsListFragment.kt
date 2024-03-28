package com.example.newsarticleapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsarticleapp.R
import com.example.newsarticleapp.adapter.ListAdapter
import com.example.newsarticleapp.bottomsheet.FilterBottomSheet
import com.example.newsarticleapp.data.NewsRepository
import com.example.newsarticleapp.data.NewsViewModel
import com.example.newsarticleapp.databinding.FragmentNewsListBinding
import com.example.newsarticleapp.model.News
import com.example.newsarticleapp.network.RemoteApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Locale


class NewsListFragment : Fragment() {

    private lateinit var _binding: FragmentNewsListBinding
    private val binding get() = _binding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsList : List<News>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val remoteApi = RemoteApi()
        val newsRepository = NewsRepository(remoteApi)
        viewModel = NewsViewModel(newsRepository)
        newsList = emptyList()

        setupObserver()

        viewModel.fetchNews()

        binding.filter.setOnClickListener {
            showPopup()
        }
    }

    private fun setupObserver() {
        viewModel.newsListLiveData.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
            newsList = it
        }
    }

    private fun setupRecyclerView(list: List<News>?) {
        if (list != null) {
            binding.rview.apply {
                adapter = ListAdapter(list, ::onItemClick)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun onItemClick(item: News) {
        val bottomSheetFragment = FilterBottomSheet(item)
        bottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetFragment.tag
        )
    }

    private fun showPopup() {
        val popup = PopupMenu(requireContext(), binding.filter)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.filter_option_menu, popup.menu)
        popup.show()

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.n2o -> {
                    val sortedNewsList = newsList.sortedByDescending { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT).parse(it.publishedAt) }
                    setupRecyclerView(sortedNewsList)
                }
                R.id.o2n -> {
                    val sortedNewsListDesc = newsList.sortedBy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT).parse(it.publishedAt) }
                    setupRecyclerView(sortedNewsListDesc)
                }
            }
            true
        }
    }
}