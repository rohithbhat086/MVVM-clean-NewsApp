package com.rgb.example.newssampleapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.rgb.example.newssampleapp.data.db.ArticleDAO
import com.rgb.example.newssampleapp.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(app, NewsDatabase::class.java, "news_db").build()
    }

    @Singleton
    @Provides
    fun providesArticleDao(database: NewsDatabase):ArticleDAO{
        return database.getArticleDao()
    }
}