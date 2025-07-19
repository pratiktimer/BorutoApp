package com.example.borutoapp.domain.repository

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.article.Article
import com.example.borutoapp.domain.model.hero.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
    fun getAllArticle(): Flow<PagingData<Article>>
    fun searchArticles(query: String): Flow<PagingData<Article>>
}