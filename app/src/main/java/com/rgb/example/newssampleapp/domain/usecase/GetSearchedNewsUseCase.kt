package com.rgb.example.newssampleapp.domain.usecase

import com.rgb.example.newssampleapp.data.model.APIResponse
import com.rgb.example.newssampleapp.data.util.Resource
import com.rgb.example.newssampleapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val repository: NewsRepository) {

    suspend fun execute(page: Int, query: String) : Resource<APIResponse> {
        return repository.getSearchedNews(page, query)
    }
}