package com.newsapi.domain.model.WeatherNewsList

import com.google.gson.annotations.SerializedName
import com.newsapi.domain.model.ArticleResponse


data class TeslaNewsList(
    @SerializedName("articles")
    val weatherNewsList: List<ArticleResponse>
)
