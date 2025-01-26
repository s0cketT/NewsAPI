package com.newsapi.presentation.news_screen.tesla_news_list

import com.newsapi.domain.model.ArticleResponse

sealed class TeslaNewsListState {
    data class Loading(val isLoading: Boolean = true) : TeslaNewsListState()
    data class Success(val news: List<ArticleResponse>) : TeslaNewsListState()
    data class Error(val error: String) : TeslaNewsListState()
}
