package com.rgb.example.newssampleapp.presentation.di

import android.app.Application
import com.rgb.example.newssampleapp.domain.usecase.*
import com.rgb.example.newssampleapp.presentation.adapter.NewsAdapter
import com.rgb.example.newssampleapp.presentation.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Singleton
    @Provides
    fun providesMainViewModelFactory(
        getTopNewsUseCase: GetTopNewsUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        getSavedArticlesUseCase: GetSavedArticlesUseCase,
        saveArticleUseCase: SaveArticleUseCase,
        deleteArticleUseCase: DeleteArticleUseCase,
        app: Application
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getTopNewsUseCase,
            getSearchedNewsUseCase,
            getSavedArticlesUseCase,
            saveArticleUseCase,
            deleteArticleUseCase,
            app
        )
    }

    @Provides
    fun provideNewNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}