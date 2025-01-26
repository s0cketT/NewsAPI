package com.newsapi.presentation.news_screen.crypto_news_list

import com.newsapi.domain.model.ArticleResponse

sealed class CryptoNewsListState {
    data class Loading(val isLoading: Boolean = true): CryptoNewsListState()
    data class Success(val news: List<ArticleResponse>): CryptoNewsListState()
    data class Error(val error: String): CryptoNewsListState()
}