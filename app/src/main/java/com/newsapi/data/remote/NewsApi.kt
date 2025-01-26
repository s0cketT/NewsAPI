package com.newsapi.data.remote

import com.newsapi.common.Constants.Crypto_Query
import com.newsapi.common.Constants.Date
import com.newsapi.common.Constants.Films_Query
import com.newsapi.common.Constants.SortBy
import com.newsapi.common.Constants.Tesla_Query
import com.newsapi.domain.model.CryptoNewsList.CryptoNewsList
import com.newsapi.domain.model.CryptoNewsList.FilmsNewsList
import com.newsapi.domain.model.WeatherNewsList.TeslaNewsList
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getTeslaNewsList(
        @Query("q") query: String = Tesla_Query,
    ): TeslaNewsList

    @GET("v2/everything")
    suspend fun getCryptoNewsList(
        @Query("from") from: String = Date,
        @Query("sortBy") sortBy: String = SortBy,
        @Query("q") query: String = Crypto_Query,
    ): CryptoNewsList

    @GET("v2/everything")
    suspend fun getFilmsNewsList(
        @Query("from") from: String = Date,
        @Query("sortBy") sortBy: String = SortBy,
        @Query("q") query: String = Films_Query,
    ): FilmsNewsList


}