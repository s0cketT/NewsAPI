package com.newsapi.domain.repository

import com.newsapi.domain.model.CryptoNewsList.CryptoNewsList
import com.newsapi.domain.model.CryptoNewsList.FilmsNewsList
import com.newsapi.domain.model.WeatherNewsList.TeslaNewsList


interface NewRepo {

    suspend fun getWeatherNewsList(): TeslaNewsList

    suspend fun getCryptNewsList(): CryptoNewsList

    suspend fun getFilmsNewsList() : FilmsNewsList
}



