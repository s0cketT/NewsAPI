package com.newsapi.data.repository

import com.newsapi.data.remote.NewsApi
import com.newsapi.domain.repository.NewsRepo
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi): NewsRepo {

    override suspend fun getTeslaNewsList() = newsApi.getTeslaNewsList()

    override suspend fun getCryptNewsList() = newsApi.getCryptoNewsList()

    override suspend fun getFilmsNewsList() = newsApi.getFilmsNewsList()

}