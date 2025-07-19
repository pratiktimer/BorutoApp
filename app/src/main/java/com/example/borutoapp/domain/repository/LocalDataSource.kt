package com.example.borutoapp.domain.repository

import com.example.borutoapp.domain.model.article.Article
import com.example.borutoapp.domain.model.hero.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
    suspend fun getSelectedArticle(heroId: Int): Article
}