package com.newsapi.presentation.news_screen.films_news_list

import com.newsapi.domain.model.ArticleResponse

sealed class FilmsNewsListState {
    data class Loading(val isLoading: Boolean = true) : FilmsNewsListState()
    data class Success(val news: List<ArticleResponse>) : FilmsNewsListState()
    data class Error(val error: String) : FilmsNewsListState()
}

