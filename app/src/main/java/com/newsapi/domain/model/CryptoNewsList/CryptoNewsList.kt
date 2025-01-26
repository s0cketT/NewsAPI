package com.newsapi.domain.model.CryptoNewsList

import com.google.gson.annotations.SerializedName
import com.newsapi.domain.model.ArticleResponse


data class CryptoNewsList(
    @SerializedName("articles")
    val cryptoNewsList: List<ArticleResponse>
)
