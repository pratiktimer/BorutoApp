package com.example.borutoapp.domain.model.article

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.borutoapp.util.Constants.ARTICLE_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = ARTICLE_DATABASE_TABLE)
data class Article(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null
)

@Serializable
data class Source(
    val id: String? = null,
    val name: String
)