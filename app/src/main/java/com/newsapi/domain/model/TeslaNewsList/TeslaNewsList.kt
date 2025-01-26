package com.newsapi.domain.model.TeslaNewsList

import com.google.gson.annotations.SerializedName
import com.newsapi.domain.model.ArticleResponse


data class TeslaNewsList(
    @SerializedName("articles")
    val teslaNewsList: List<ArticleResponse>
)
