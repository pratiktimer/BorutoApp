package com.example.borutoapp.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome_screen")
    data object Home : Screen("home_screen")
    data object Details : Screen("details_screen/{heroId}") {
        fun passHeroId(heroId: Int): String {
            return "details_screen/$heroId"
        }
    }

    data object Search : Screen("search_screen")
}
