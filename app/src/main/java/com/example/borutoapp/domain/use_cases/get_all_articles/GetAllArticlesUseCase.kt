package com.example.borutoapp.domain.use_cases.get_all_articles

import androidx.paging.PagingData
import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.model.article.Article
import com.example.borutoapp.domain.model.hero.Hero
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Article>> {
        return repository.getAllArticles()
    }
}