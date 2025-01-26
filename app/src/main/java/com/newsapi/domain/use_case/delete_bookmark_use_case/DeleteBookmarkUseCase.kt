package com.newsapi.domain.use_case.delete_bookmark_use_case

import com.newsapi.data.database.bookmark.BookmarkDB
import com.newsapi.domain.repository.BookmarksRepo
import javax.inject.Inject


class DeleteBookmarkUseCase @Inject constructor(private val bookmarksRepository: BookmarksRepo) {
    suspend operator fun invoke(bookmark: BookmarkDB) {
        bookmarksRepository.deleteBookmark(bookmark)
    }
}