package com.example.borutoapp.data.repository

import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.domain.model.article.Article
import com.example.borutoapp.domain.model.hero.Hero
import com.example.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase): LocalDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }

    override suspend fun getSelectedArticle(heroId: Int): Article {
        TODO("Not yet implemented")
    }
}