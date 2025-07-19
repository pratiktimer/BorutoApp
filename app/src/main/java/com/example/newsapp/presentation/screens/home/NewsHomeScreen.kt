package com.example.newsapp.presentation.screens.home

import android.app.Activity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.ui.theme.statusBarColor
import com.example.borutoapp.ui.theme.welcomeScreenBackgroundColor

@ExperimentalCoilApi
@Composable
fun NewsHomeScreen(
    navController: NavHostController,
    newsHomeViewModel: NewsHomeViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val allHeroes = newsHomeViewModel.getAllArticles.collectAsLazyPagingItems()
    val systemBarColor = statusBarColor.toArgb()

    SideEffect { activity.window.statusBarColor = systemBarColor }

    Scaffold(
        topBar = {
            NewsHomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        containerColor = welcomeScreenBackgroundColor,
        content = { padding ->
            NewsListContent(
                padding = padding,
                heroes = allHeroes,
                navController = navController
            )
        }
    )
}