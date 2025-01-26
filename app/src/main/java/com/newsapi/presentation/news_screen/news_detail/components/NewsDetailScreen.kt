package com.newsapi.presentation.news_screen.news_detail.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.presentation.news_screen.NewsViewModel
import com.newsapi.presentation.news_screen.news_detail.NewsDetailViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsDetailScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
    newsDetailViewModel: NewsDetailViewModel = hiltViewModel()
) {

    val width = LocalConfiguration.current.screenWidthDp

    //получаем объекст при навигации
    val newsJson = navBackStackEntry.arguments?.getString("newsJson")
    //декодируем объект
    val news = newsDetailViewModel.getNewsFromJson(newsJson)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (news != null) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                stickyHeader {
                    TopBarNewsDetail(navController, news)
                }

                item {
                    NewsDetail(news)
                    Spacer(modifier = Modifier.size((width * 0.05).dp))
                }

                item {
                    FountainNews(news)
                }

            }
        }
        else {
            Text(text = "Data retrieval error")
        }
    }

}