package com.newsapi.presentation.bookmarks_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.newsapi.data.database.bookmark.BookmarkDB
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.domain.model.Source
import com.newsapi.domain.use_case.delete_bookmark_use_case.DeleteBookmarkUseCase
import com.newsapi.domain.use_case.get_all_bookmarks_use_case.GetAllBookmarksUseCase
import com.newsapi.domain.use_case.insert_bookmarks_use_case.InsertBookmarkUseCase
import com.newsapi.domain.use_case.is_bookmarks_exist_use_case.IsBookmarksExistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getAllBookmarksUseCase: GetAllBookmarksUseCase,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    private val isBookmarksExistUseCase: IsBookmarksExistUseCase
) : ViewModel() {

    private val _bookmarksList = mutableStateOf<List<BookmarkDB>>(emptyList())
    val bookmarksList: State<List<BookmarkDB>> = _bookmarksList

    private val _isExist = mutableStateOf(false)
    val isExist: State<Boolean> = _isExist
    fun onExist(exist: Boolean) { _isExist.value = exist }

    private val _isExistId = mutableStateOf(-1)
    val isExistId: State<Int> = _isExistId
    fun onExistId(existId: Int) { _isExistId.value = existId }

    init {
        loadBookmarks()
    }

    private fun loadBookmarks() {
        viewModelScope.launch {
            getAllBookmarksUseCase().collect { bookmarks ->
                _bookmarksList.value = bookmarks
            }
        }
    }

    fun insertBookmark(bookmark: BookmarkDB) {
        viewModelScope.launch {
            insertBookmarkUseCase(bookmark)
        }
    }

    fun deleteBookmark(bookmark: BookmarkDB) {
        viewModelScope.launch {
            deleteBookmarkUseCase(bookmark)
        }
    }

    suspend fun isBookmarkExists(newUrl: String): Int? {
        return isBookmarksExistUseCase(newUrl)
    }

    //кодируем объект для передачи в navController
    fun getEncodedJsonForBookmarkDetail(bookmark: BookmarkDB): String {
        // Сериализуем объект в строку JSON
        val newsJson = Gson().toJson(
            ArticleResponse(
                Source(null, bookmark.sourceName),
                bookmark.author,
                bookmark.title,
                bookmark.description,
                bookmark.url,
                bookmark.urlToImage,
                bookmark.publishedAt,
                bookmark.content,
            )
        )

        // Убираем некорректные символы
        val sanitizedJson = newsJson
            .replace("%", " percentage")
            .replace("[^\\x20-\\x7E]", "") // Убираем все символы, которые не являются ASCII

        // Кодируем строку JSON в безопасный для URL формат
        return try {
            URLEncoder.encode(sanitizedJson, StandardCharsets.UTF_8.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}

