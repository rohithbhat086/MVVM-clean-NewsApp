package com.rgb.example.newssampleapp.data.repository.datasource.impl

import com.rgb.example.newssampleapp.data.api.NewsService
import com.rgb.example.newssampleapp.data.model.APIResponse
import com.rgb.example.newssampleapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(private val newsService: NewsService) : NewsRemoteDataSource {

    override suspend fun getTopNews(page: Int): Response<APIResponse> {
        return newsService.getTopHeadlines(page)
    }

    override suspend fun getSearchedNews(page: Int, query: String): Response<APIResponse> {
        return newsService.getSearchedTopHeadlines(page = page, query = query)
    }


}