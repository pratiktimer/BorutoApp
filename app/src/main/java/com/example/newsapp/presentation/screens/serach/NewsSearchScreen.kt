package com.example.newsapp.presentation.screens.serach

import android.app.Activity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.borutoapp.presentation.common.ListContent
import com.example.borutoapp.presentation.screens.search.SearchTopBar
import com.example.borutoapp.presentation.screens.search.SearchViewModel
import com.example.borutoapp.ui.theme.statusBarColor
import com.example.newsapp.presentation.screens.home.NewsListContent

@ExperimentalCoilApi
@Composable
fun NewsSearchScreen(
    navController: NavHostController,
    searchViewModel: NewsSearchViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()
    val systemBarColor = statusBarColor.toArgb()

    SideEffect { activity.window.statusBarColor = systemBarColor }

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = { padding ->
            NewsListContent(
                padding = padding,
                heroes = heroes,
                navController = navController
            )
        }
    )
}