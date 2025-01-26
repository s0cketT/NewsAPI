package com.newsapi.domain.repository

import com.newsapi.domain.model.CryptoNewsList.CryptoNewsList
import com.newsapi.domain.model.CryptoNewsList.FilmsNewsList
import com.newsapi.domain.model.TeslaNewsList.TeslaNewsList


interface NewsRepo {

    suspend fun getTeslaNewsList(): TeslaNewsList

    suspend fun getCryptNewsList(): CryptoNewsList

    suspend fun getFilmsNewsList() : FilmsNewsList
}



