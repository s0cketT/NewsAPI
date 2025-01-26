package com.newsapi.domain.repository

import com.newsapi.data.database.bookmark.BookmarkDB
import kotlinx.coroutines.flow.Flow

interface BookmarksRepo {

    suspend fun getAllBookmarks(): Flow<List<BookmarkDB>>
    suspend fun isBookmarkExists(newUrl: String): Int?
    suspend fun insertBookmark(bookmark: BookmarkDB)
    suspend fun deleteBookmark(bookmark: BookmarkDB)

}