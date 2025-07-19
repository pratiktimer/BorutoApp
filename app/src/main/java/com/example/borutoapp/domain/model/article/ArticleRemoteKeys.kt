package com.example.borutoapp.domain.model.article

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.borutoapp.util.Constants.ARTICLE_REMOTE_KEYS_DATABASE_TABLE


@Entity(tableName = ARTICLE_REMOTE_KEYS_DATABASE_TABLE)
data class ArticleRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
