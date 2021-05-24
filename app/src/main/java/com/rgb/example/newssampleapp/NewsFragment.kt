package com.rgb.example.newssampleapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.data.util.Resource
import com.rgb.example.newssampleapp.databinding.FragmentNewsBinding
import com.rgb.example.newssampleapp.presentation.adapter.NewsAdapter
import com.rgb.example.newssampleapp.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var mViewModel: MainViewModel
    private var page = 0
    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        mViewModel = (activity as MainActivity).mViewModel
        initNewsList()
        viewNewsData()
        setupSearch()
    }

    private fun setupSearch() {
        binding.svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                mViewModel.getSearchedNews(page, p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun initNewsList() {
        newsAdapter.setOnClickCallback(::onNewsArticleClicked)
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
    }

    private fun onNewsArticleClicked(article: Article){
//        Toast.makeText(activity, article.title, Toast.LENGTH_SHORT).show()
        val bundle =  Bundle().apply {
            putSerializable("article", article)
        }
        findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
    }

    private fun viewNewsData() {
        mViewModel.getTopNews(page)
        mViewModel.topNews.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    Log.i("MyNews", "Loading...")
                    onLoadingState(true)
                }
                is Resource.Error -> {
                    Log.i("MyNews", "Error ${it.message}")
                    onLoadingState(false)
                    it.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Success -> {
                    Log.i("MyNews", "Success. ${it.data?.articles?.size}")
                    onLoadingState(false)
                    it.data?.articles.let { list ->
                        newsAdapter.differ.submitList(list)
                    }
                }
            }
        })
    }

    private fun onLoadingState(isLoading: Boolean) {
        var visibility = View.VISIBLE
        if (!isLoading)
            visibility = View.GONE
        binding.pbLoading.visibility = visibility
    }

}