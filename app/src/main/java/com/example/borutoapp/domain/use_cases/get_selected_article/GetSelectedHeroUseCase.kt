package com.example.borutoapp.domain.use_cases.get_selected_article

import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.model.article.Article


class GetSelectedArticleUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Article {
        return repository.getSelectedArticle(heroId = heroId)
    }
}