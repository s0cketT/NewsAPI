package com.newsapi.di

import com.newsapi.data.repository.BookmarksRepository
import com.newsapi.domain.repository.BookmarksRepo
import com.newsapi.domain.use_case.delete_bookmark_use_case.DeleteBookmarkUseCase
import com.newsapi.domain.use_case.get_all_bookmarks_use_case.GetAllBookmarksUseCase
import com.newsapi.domain.use_case.insert_bookmarks_use_case.InsertBookmarkUseCase
import com.newsapi.domain.use_case.is_bookmarks_exist_use_case.IsBookmarksExistUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object BookmarkRepoModule {

    @Provides
    fun provideBookmarksRepository(database: MainDatabase): BookmarksRepo {
        return BookmarksRepository(database)
    }

    @Provides
    fun provideInsertBookmarkUseCase(bookmarksRepository: BookmarksRepo): InsertBookmarkUseCase {
        return InsertBookmarkUseCase(bookmarksRepository)
    }

    @Provides
    fun provideDeleteBookmarkUseCase(bookmarksRepository: BookmarksRepo): DeleteBookmarkUseCase {
        return DeleteBookmarkUseCase(bookmarksRepository)
    }

    @Provides
    fun provideCheckBookmarkExistenceUseCase(bookmarksRepository: BookmarksRepo): IsBookmarksExistUseCase {
        return IsBookmarksExistUseCase(bookmarksRepository)
    }

    @Provides
    fun provideGetAllBookmarksUseCase(bookmarksRepository: BookmarksRepo): GetAllBookmarksUseCase {
        return GetAllBookmarksUseCase(bookmarksRepository)
    }
}
