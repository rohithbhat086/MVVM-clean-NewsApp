package com.rgb.example.newssampleapp.data.repository.datasource

import com.rgb.example.newssampleapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun saveArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getSavedArticles(): Flow<List<Article>>
}