package com.rgb.example.newssampleapp.data.model


import com.google.gson.annotations.SerializedName
import com.rgb.example.newssampleapp.data.util.Resource

data class APIResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)