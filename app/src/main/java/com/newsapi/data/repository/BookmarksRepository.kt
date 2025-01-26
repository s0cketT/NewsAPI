package com.newsapi.data.repository

import com.newsapi.data.database.bookmark.BookmarkDB
import com.newsapi.di.MainDatabase
import com.newsapi.domain.repository.BookmarksRepo
import javax.inject.Inject

class BookmarksRepository @Inject constructor(private val database: MainDatabase): BookmarksRepo {

    override suspend fun getAllBookmarks() = database.daoBookmark.getAllBookmarks()

    override suspend fun isBookmarkExists(newUrl: String): Int? {
        return database.daoBookmark.getIdByUrl(newUrl)
    }

    override suspend fun insertBookmark(bookmark: BookmarkDB) {
        database.daoBookmark.insertBookmark(bookmark)
    }

    override suspend fun deleteBookmark(bookmark: BookmarkDB) {
        database.daoBookmark.deleteBookmark(bookmark)
    }
}
