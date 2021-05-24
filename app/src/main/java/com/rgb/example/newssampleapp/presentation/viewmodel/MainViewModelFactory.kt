package com.rgb.example.newssampleapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rgb.example.newssampleapp.domain.usecase.*

class MainViewModelFactory(
    private val getTopNewsUseCase: GetTopNewsUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val saveArticleUseCase: SaveArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val app: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            getTopNewsUseCase,
            getSearchedNewsUseCase,
            getSavedArticlesUseCase,
            saveArticleUseCase,
            deleteArticleUseCase,
            app) as T
    }
}