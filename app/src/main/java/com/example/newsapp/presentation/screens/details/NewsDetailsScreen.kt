package com.example.newsapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.borutoapp.presentation.screens.details.UiEvent
import com.example.borutoapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.example.borutoapp.util.PaletteGenerator.extractColorsFromBitmap

@ExperimentalCoilApi
@Composable
fun NewsDetailsScreen(
    navController: NavHostController,
    detailsViewModel: NewsDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette
    val uiEvent by detailsViewModel.uiEvent

    LaunchedEffect(key1 = uiEvent) {
        when (uiEvent) {
            is UiEvent.GenerateColorPalette -> {
                val bitmap = selectedHero?.urlToImage?.let {
                    convertImageUrlToBitmap(
                        imageUrl = it,
                        context = context
                    )
                }
                if (bitmap != null) {
                    detailsViewModel.setColorPalette(
                        colors = extractColorsFromBitmap(
                            bitmap = bitmap
                        )
                    )
                }
            }
            else -> {}
        }
    }

    if (colorPalette.isNotEmpty()) {
        NewsDetailsContent(
            navController = navController,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    } else {
        detailsViewModel.generateColorPalette()
    }
}