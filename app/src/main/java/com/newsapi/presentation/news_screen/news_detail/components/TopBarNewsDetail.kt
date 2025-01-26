package com.newsapi.presentation.news_screen.news_detail.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newsapi.data.database.bookmark.BookmarkDB
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.presentation.bookmarks_screen.BookmarksViewModel
import com.newsapi.presentation.news_screen.news_detail.NewsDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TopBarNewsDetail(
    navController: NavController,
    news: ArticleResponse,
    bookmarksViewModel: BookmarksViewModel = hiltViewModel()
) {

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    val context = LocalContext.current

    //запускаем LaunchedEffect при изменении состояния
    LaunchedEffect(bookmarksViewModel.isExist.value) {
        //проверяем существует ли объект
        val bookmarkId = bookmarksViewModel.isBookmarkExists(news.url)

        if (bookmarkId == null) {
            //элемент не найдет, делаем иконку закладки неактивной

            bookmarksViewModel.onExistId(-1)
            bookmarksViewModel.onExist(false)
        }
        else {
            //элемент не найдет, делаем иконку закладки активной

            bookmarksViewModel.onExistId(bookmarkId)
            bookmarksViewModel.onExist(true)
        }

    }

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .size((width * 0.9).dp, (height * 0.08).dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.size((width * 0.08).dp)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Favorite Icon",
                tint = Color.Gray,
                modifier = Modifier.fillMaxSize()
            )
        }

        news.author?.let {
            Text(
                text = it,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.W500
            )
        }

        IconButton(
            onClick = {

                if (bookmarksViewModel.isExistId.value == -1) {

                    //добавляем новость в закладку
                    bookmarksViewModel.insertBookmark(
                        BookmarkDB(
                            id = 0,
                            sourceName = news.source.name,
                            author = news.author ?: "",
                            title = news.title ,
                            description = news.description ?: "",
                            url = news.url,
                            urlToImage = news.urlToImage ?: "",
                            publishedAt = news.publishedAt,
                            content = news.content ?: ""
                        )
                    )

                    Toast.makeText(context, "Added to Bookmarks", Toast.LENGTH_SHORT).show()

                    bookmarksViewModel.onExist(true)
                }
                else {

                    //удаляем новость из закладки
                    bookmarksViewModel.deleteBookmark(
                        BookmarkDB(
                            id = bookmarksViewModel.isExistId.value,
                            sourceName = news.source.name,
                            author = news.author ?: "",
                            title = news.title ,
                            description = news.description ?: "",
                            url = news.url,
                            urlToImage = news.urlToImage ?: "",
                            publishedAt = news.publishedAt,
                            content = news.content ?: ""
                        )
                    )

                    Toast.makeText(context, "Deleted from Bookmarks", Toast.LENGTH_SHORT).show()

                    bookmarksViewModel.onExist(false)
                }
            },
            modifier = Modifier.size((width * 0.07).dp)
        ) {
            Icon(
                imageVector = if (bookmarksViewModel.isExist.value) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}