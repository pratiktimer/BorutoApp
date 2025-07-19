package com.example.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.paging_source.article.ArticleRemoteMediator
import com.example.borutoapp.data.paging_source.article.SearchArticlesSource
import com.example.borutoapp.data.paging_source.hero.HeroRemoteMediator
import com.example.borutoapp.data.paging_source.hero.SearchHeroesSource
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.domain.model.article.Article
import com.example.borutoapp.domain.model.hero.Hero
import com.example.borutoapp.domain.repository.RemoteDataSource
import com.example.borutoapp.util.Constants.ITEMS_PER_PAGE
import com.example.borutoapp.util.Constants.ITEMS_PER_PAGE_ARTICLE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {

    private val heroDao = borutoDatabase.heroDao()
    private val articleDao = borutoDatabase.articleDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(borutoApi = borutoApi, query = query)
            }
        ).flow
    }

    override fun getAllArticle(): Flow<PagingData<Article>> {
        val pagingSourceFactory = { articleDao.getAllArticles() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE_ARTICLE),
            remoteMediator = ArticleRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchArticles(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE_ARTICLE),
            pagingSourceFactory = {
                SearchArticlesSource(borutoApi = borutoApi, query = query)
            }
        ).flow
    }
}