package com.example.borutoapp.data.local.dao.article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.borutoapp.domain.model.article.ArticleRemoteKeys


@Dao
interface ArticleRemoteKeysDao {

    @Query("SELECT * FROM article_remote_keys_table WHERE id = :heroId")
    suspend fun getRemoteKeys(heroId: Int): ArticleRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<ArticleRemoteKeys>)

    @Query("DELETE FROM article_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
    @Query("SELECT * FROM article_remote_keys_table")
    suspend fun getAllKeys(): List<ArticleRemoteKeys>

    @Query("SELECT COUNT(*) FROM article_table")
    suspend fun getAllArticlesCount(): Int

}