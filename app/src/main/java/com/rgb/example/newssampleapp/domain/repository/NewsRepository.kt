package com.rgb.example.newssampleapp.domain.repository

import com.rgb.example.newssampleapp.data.model.APIResponse
import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopNews(page: Int): Resource<APIResponse>

    suspend fun getSearchedNews(page: Int, query: String): Resource<APIResponse>

    suspend fun saveArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getSavedArticles(): Flow<List<Article>>
}