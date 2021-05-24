package com.rgb.example.newssampleapp.presentation.di

import com.rgb.example.newssampleapp.data.repository.NewsRepositoryImpl
import com.rgb.example.newssampleapp.data.repository.datasource.NewsLocalDataSource
import com.rgb.example.newssampleapp.data.repository.datasource.NewsRemoteDataSource
import com.rgb.example.newssampleapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}