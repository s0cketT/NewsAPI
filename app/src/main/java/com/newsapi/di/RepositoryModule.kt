package com.newsapi.di

import com.newsapi.data.repository.NewsRepository
import com.newsapi.domain.repository.NewRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewRepo(newsRepository: NewsRepository): NewRepo
}