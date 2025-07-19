package com.example.borutoapp.data.remote

import com.example.borutoapp.domain.model.ApiResponse
import com.example.borutoapp.domain.model.article.Article
import com.example.borutoapp.domain.model.hero.Hero
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse<Hero>

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse<Hero>

    @GET("/boruto/articles")
    suspend fun getAllArticles(
        @Query("page") page: Int = 1
    ): ApiResponse<Article>

    @GET("/boruto/articles/search")
    suspend fun searchArticles(
        @Query("title") name: String
    ): ApiResponse<Article>

}