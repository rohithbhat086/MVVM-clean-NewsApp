package com.rgb.example.newssampleapp.data.repository.datasource.impl

import com.rgb.example.newssampleapp.data.db.ArticleDAO
import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(private val dao: ArticleDAO) : NewsLocalDataSource {

    override suspend fun saveArticle(article: Article) {
        dao.insert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        dao.delete(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return dao.getAllArticles()
    }
}