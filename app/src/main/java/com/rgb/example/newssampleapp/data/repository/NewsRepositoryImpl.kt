package com.rgb.example.newssampleapp.data.repository

import com.rgb.example.newssampleapp.data.model.APIResponse
import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.data.repository.datasource.NewsLocalDataSource
import com.rgb.example.newssampleapp.data.repository.datasource.NewsRemoteDataSource
import com.rgb.example.newssampleapp.data.util.Resource
import com.rgb.example.newssampleapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun getTopNews(page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopNews(page))
    }

    override suspend fun getSearchedNews(page: Int, query: String): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedNews(page, query))
    }

    override suspend fun saveArticle(article: Article) {
        newsLocalDataSource.saveArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsLocalDataSource.deleteArticle(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticles()
    }

    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(null, "Error: ${response.code().toString()}")
    }
}