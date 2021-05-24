package com.rgb.example.newssampleapp.data.repository.datasource

import com.rgb.example.newssampleapp.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopNews(page: Int): Response<APIResponse>

    suspend fun getSearchedNews(page: Int, query: String): Response<APIResponse>
}