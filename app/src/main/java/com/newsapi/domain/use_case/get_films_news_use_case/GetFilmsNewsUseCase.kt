package com.newsapi.domain.use_case.get_films_news_use_case

import com.newsapi.common.Resource
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmsNewsUseCase @Inject constructor(private val newsRepository: NewsRepo) {
    operator fun invoke(): Flow<Resource<List<ArticleResponse>>> = flow {
        try {
            emit(Resource.Loading())
            val newsArticles = newsRepository.getFilmsNewsList().filmsNewsList // Получаем список статей
            emit(Resource.Success(newsArticles))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}
