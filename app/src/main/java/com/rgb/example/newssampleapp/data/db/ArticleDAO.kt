package com.rgb.example.newssampleapp.data.db

import androidx.room.*
import com.rgb.example.newssampleapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles() : Flow<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}