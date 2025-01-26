package com.newsapi.data.repository

import com.newsapi.data.remote.NewsApi
import com.newsapi.domain.repository.NewRepo
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi): NewRepo {

    override suspend fun getWeatherNewsList() = newsApi.getTeslaNewsList()

    override suspend fun getCryptNewsList() = newsApi.getCryptoNewsList()

    override suspend fun getFilmsNewsList() = newsApi.getFilmsNewsList()

}