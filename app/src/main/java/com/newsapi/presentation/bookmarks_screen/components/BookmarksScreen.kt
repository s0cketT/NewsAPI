package com.newsapi.presentation.bookmarks_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newsapi.presentation.bookmarks_screen.BookmarksViewModel
import com.newsapi.presentation.news_screen.components.NewsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarksScreen(
    navController: NavController,
    bookmarksViewModel: BookmarksViewModel = hiltViewModel()
) {

    val bookmarksList = bookmarksViewModel.bookmarksList

    val width = LocalConfiguration.current.screenWidthDp

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        stickyHeader {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Saved news list",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.W500
                )

                Spacer(modifier = Modifier.size((width * 0.05).dp))
            }
        }


        items(bookmarksList.value) { bookmark ->
            BookmarkItem(bookmark, navController)
        }
    }

}