package com.newsapi.domain.use_case.get_all_bookmarks_use_case

import com.newsapi.data.database.bookmark.BookmarkDB
import com.newsapi.domain.repository.BookmarksRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllBookmarksUseCase @Inject constructor(private val bookmarksRepository: BookmarksRepo) {
    suspend operator fun invoke(): Flow<List<BookmarkDB>> {
        return bookmarksRepository.getAllBookmarks()
    }
}