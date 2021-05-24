package com.rgb.example.newssampleapp.presentation.di

import com.rgb.example.newssampleapp.data.api.NewsService
import com.rgb.example.newssampleapp.data.db.ArticleDAO
import com.rgb.example.newssampleapp.data.repository.datasource.NewsLocalDataSource
import com.rgb.example.newssampleapp.data.repository.datasource.NewsRemoteDataSource
import com.rgb.example.newssampleapp.data.repository.datasource.impl.NewsLocalDataSourceImpl
import com.rgb.example.newssampleapp.data.repository.datasource.impl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsService: NewsService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsService)
    }

    @Singleton
    @Provides
    fun provideNewsLocalDataSource(articleDAO: ArticleDAO):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articleDAO)
    }
}