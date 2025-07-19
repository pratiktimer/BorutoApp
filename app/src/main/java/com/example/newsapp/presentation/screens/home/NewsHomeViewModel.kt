package com.example.newsapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.example.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsHomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllArticles = useCases.getAllArticlesUseCase()
}