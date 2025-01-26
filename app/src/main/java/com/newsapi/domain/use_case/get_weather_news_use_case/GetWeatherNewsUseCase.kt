package com.newsapi.domain.use_case.get_weather_news_use_case

import com.newsapi.common.Resource
import com.newsapi.data.repository.NewsRepository
import com.newsapi.domain.model.ArticleResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<Resource<List<ArticleResponse>>> = flow {
        try {
            emit(Resource.Loading())
            val newsArticles = newsRepository.getWeatherNewsList().weatherNewsList // Получаем список статей
            emit(Resource.Success(newsArticles))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}
