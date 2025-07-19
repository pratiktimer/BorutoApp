package com.example.borutoapp.data.paging_source.article

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.domain.model.article.Article
import java.lang.Exception

class SearchArticlesSource(
    private val borutoApi: BorutoApi,
    private val query: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val apiResponse = borutoApi.searchArticles(name = query)
            val heroes = apiResponse.data
            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
}