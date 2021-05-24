package com.rgb.example.newssampleapp.domain.usecase

import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedArticlesUseCase(private val newsRepository: NewsRepository) {

    fun execute():Flow<List<Article>> = newsRepository.getSavedArticles()
}