package com.rgb.example.newssampleapp.presentation.di

import com.rgb.example.newssampleapp.domain.repository.NewsRepository
import com.rgb.example.newssampleapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetTopNewsUseCase(newsRepository: NewsRepository): GetTopNewsUseCase{
        return GetTopNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesGetSearchedNewsUseCase(newsRepository: NewsRepository): GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesSaveArticleUseCase(newsRepository: NewsRepository): SaveArticleUseCase{
        return SaveArticleUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesDeleteArticleUseCase(newsRepository: NewsRepository): DeleteArticleUseCase{
        return DeleteArticleUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesGetSavedArticlesUseCase(newsRepository: NewsRepository): GetSavedArticlesUseCase{
        return GetSavedArticlesUseCase(newsRepository)
    }
}