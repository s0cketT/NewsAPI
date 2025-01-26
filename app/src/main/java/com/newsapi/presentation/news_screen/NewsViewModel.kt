package com.newsapi.presentation.news_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.CurrencyBitcoin
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.ElectricCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.newsapi.data.database.bookmark.BookmarkDB
import com.newsapi.di.MainDatabase
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.domain.model.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(): ViewModel() {

    private val _tabItems = listOf(
        TabItem(
            title = "Tesla",
            unselectedIcon = Icons.Outlined.ElectricCar,
            selectedIcon = Icons.Default.ElectricCar
        ),
        TabItem(
            title = "Crypto",
            unselectedIcon = Icons.Outlined.CurrencyBitcoin,
            selectedIcon = Icons.Default.CurrencyBitcoin
        ),
        TabItem(
            title = "Films",
            unselectedIcon = Icons.Outlined.Movie,
            selectedIcon = Icons.Default.Movie
        )
    )
    val tabItems: List<TabItem> get() = _tabItems

    private val _selectedTabIndex = mutableStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex

    // Установка выбранной вкладки
    fun selectTab(index: Int) {
        _selectedTabIndex.value = index
    }

    //кодируем объект для передачи в navController
    fun getEncodedJsonForNewsDetail(news: ArticleResponse): String {
        // Сериализуем объект в строку JSON
        val newsJson = Gson().toJson(
            ArticleResponse(
                Source(null, news.source.name),
                news.author,
                news.title,
                news.description,
                news.url,
                news.urlToImage,
                news.publishedAt,
                news.content,
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