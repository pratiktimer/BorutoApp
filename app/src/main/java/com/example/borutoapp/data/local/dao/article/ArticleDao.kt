package com.example.borutoapp.data.local.dao.article

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.borutoapp.domain.model.article.Article


@Dao
interface ArticleDao {

    @Query("SELECT * FROM article_table ORDER BY id ASC")
    fun getAllArticles(): PagingSource<Int, Article>

    @Query("SELECT * FROM article_table WHERE id=:heroId")
    fun getSelectedArticle(heroId: Int): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(heroes: List<Article>)

    @Query("DELETE FROM article_table")
    suspend fun deleteAllArticles()

}