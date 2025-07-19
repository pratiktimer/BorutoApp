package com.example.newsapp.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.article.Article
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.presentation.components.ShimmerEffect
import com.example.borutoapp.ui.theme.ARTICLE_ITEM_HEIGHT
import com.example.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.topAppBarContentColor

@ExperimentalCoilApi
@Composable
fun NewsListContent(
    padding: PaddingValues,
    heroes: LazyPagingItems<Article>,
    navController: NavHostController
) {
    val result = handlePagingResult(heroes = heroes)

    if (result) {
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = heroes.itemSnapshotList.items,
                key = { hero ->
                    hero.id
                }
            ) { hero ->
                NewsHeroItem(hero = hero, navController = navController)
            }
        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Article>
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                Text(error.toString())
               // EmptyScreen(error = error, heroes = heroes)
                false
            }
            heroes.itemCount < 1 -> {
                Text("no items")
                //EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun NewsHeroItem(
    hero: Article,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .height(ARTICLE_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },

    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(
                topStart = LARGE_PADDING,
                topEnd = LARGE_PADDING,
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Row() {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(1f)
                        .fillMaxWidth(0.4f),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = hero.urlToImage)
                        .placeholder(drawableResId = R.drawable.ic_placeholder)
                        .error(drawableResId = R.drawable.ic_placeholder)
                        .build(),
                    contentDescription = stringResource(id = R.string.hero_image),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = MEDIUM_PADDING)
                ) {
                    Text(
                        text = hero.title,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    hero.description?.let {
                        Text(
                            text = it,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }
            }
        }
    }
}

//@ExperimentalCoilApi
//@Composable
//@Preview
//fun NewsHeroItemPreview() {
//    NewsHeroItem(
//        hero = Article(
//            id = 1,
//            name = "Sasuke",
//            image = "",
//            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
//            rating = 0.0,
//            power = 100,
//            month = "",
//            day = "",
//            family = listOf(),
//            abilities = listOf(),
//            natureTypes = listOf()
//        ),
//        navController = rememberNavController()
//    )
//}
//
//@ExperimentalCoilApi
//@Composable
//@Preview(uiMode = UI_MODE_NIGHT_YES)
//fun HeroItemDarkPreview() {
//    NewsHeroItem(
//        hero = Article(
//            id = 1,
//            name = "Sasuke",
//            image = "",
//            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
//            rating = 0.0,
//            power = 100,
//            month = "",
//            day = "",
//            family = listOf(),
//            abilities = listOf(),
//            natureTypes = listOf()
//        ),
//        navController = rememberNavController()
//    )
//}