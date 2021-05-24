package com.rgb.example.newssampleapp.data.api

import com.rgb.example.newssampleapp.BuildConfig
import com.rgb.example.newssampleapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    //TODO Make country configurable by user. Add UI and save user's choice in pref.

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("page")
        page: Int,
        @Query("country")
        country: String = "in",
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ) : Response<APIResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("q")
        query:String,
        @Query("page")
        page: Int,
        @Query("country")
        country: String = "in",
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ) : Response<APIResponse>

}