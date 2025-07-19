package com.example.newsapp.presentation.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.borutoapp.ui.theme.statusBarColor
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor
import com.example.newsapp.presentation.screens.serach.NewsSearchViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NewsHomeScreen(
    navController: NavHostController,
    newsHomeViewModel: NewsHomeViewModel = hiltViewModel(),
    searchViewModel: NewsSearchViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val systemBarColor = statusBarColor.toArgb()
    val searchQuery by searchViewModel.searchQuery

    // Articles from respective sources
    val allArticles = newsHomeViewModel.getAllArticles.collectAsLazyPagingItems()
    val searchedArticles = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    // Change system status bar color
    SideEffect { activity.window.statusBarColor = systemBarColor }

    Scaffold(
        containerColor = topAppBarBackgroundColor,
        topBar = {
        NewsHomeTopBar(
            onSearchClicked = {

            }
        )
    },
        // ✅ Removed topBar completely
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {

                // ✅ Always visible search bar
                NewsSearch(
                    text = searchQuery,
                    onTextChange = {
                        searchViewModel.updateSearchQuery(it)
                        if (it.isNotEmpty()) {
                            searchViewModel.searchHeroes(it)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // ✅ Show either normal or searched list
                NewsListContent(
                    padding = PaddingValues(0.dp),
                    heroes = if (searchQuery.isEmpty()) allArticles else searchedArticles,
                    navController = navController
                )

            }
        }
    )
}
