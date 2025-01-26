package com.newsapi.di

import com.newsapi.common.Constants.API_KEY
import com.newsapi.common.Constants.BASE_URL
import com.newsapi.common.Constants.Date
import com.newsapi.common.Constants.SortBy
import com.newsapi.data.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestWithHeaders = originalRequest.newBuilder()
                    .addHeader("Authorization", API_KEY)
                    .build()
                chain.proceed(requestWithHeaders)
            }
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Используем OkHttpClient с Interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

}