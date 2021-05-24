package com.rgb.example.newssampleapp.domain.usecase

import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.domain.repository.NewsRepository

class SaveArticleUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.saveArticle(article)
}