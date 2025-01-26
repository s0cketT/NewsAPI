package com.newsapi.domain.use_case.is_bookmarks_exist_use_case

import com.newsapi.domain.repository.BookmarksRepo
import javax.inject.Inject


class IsBookmarksExistUseCase @Inject constructor(private val bookmarksRepository: BookmarksRepo) {
    suspend operator fun invoke(newUrl: String): Int? {
        return bookmarksRepository.isBookmarkExists(newUrl)
    }
}