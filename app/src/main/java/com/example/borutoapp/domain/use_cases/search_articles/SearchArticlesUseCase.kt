package com.example.borutoapp.domain.use_cases.search_articles

import androidx.paging.PagingData
import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.model.article.Article

import kotlinx.coroutines.flow.Flow

class SearchArticlesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Article>> {
        return repository.searchArticles(query = query)
    }
}