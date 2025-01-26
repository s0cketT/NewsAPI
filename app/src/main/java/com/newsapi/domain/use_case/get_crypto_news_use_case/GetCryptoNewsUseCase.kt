package com.newsapi.domain.use_case.get_crypto_news_use_case

import com.newsapi.common.Resource
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.domain.repository.NewRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCryptoNewsUseCase @Inject constructor(private val newsRepository: NewRepo) {
    operator fun invoke(): Flow<Resource<List<ArticleResponse>>> = flow {
        try {
            emit(Resource.Loading())
            val newsArticles = newsRepository.getCryptNewsList().cryptoNewsList // Получаем список статей
            emit(Resource.Success(newsArticles))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}
