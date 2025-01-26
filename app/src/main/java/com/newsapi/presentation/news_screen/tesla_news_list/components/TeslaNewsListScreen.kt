package com.newsapi.presentation.news_screen.tesla_news_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newsapi.presentation.news_screen.NewsViewModel
import com.newsapi.presentation.news_screen.tesla_news_list.TeslaNewsListViewModel
import com.newsapi.presentation.news_screen.tesla_news_list.TeslaNewsListState

@Composable
fun WeatherNewsListScreen(
    navController: NavController,
    newsListViewModel: TeslaNewsListViewModel = hiltViewModel(),
) {

    val teslaNewsList = newsListViewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {

        when(teslaNewsList) {
            is TeslaNewsListState.Loading -> CircularProgressIndicator()
            is TeslaNewsListState.Error -> {
                Text(
                    text = teslaNewsList.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            is TeslaNewsListState.Success -> {
                TeslaNewsList(teslaNewsList.news, navController)
            }
        }

    }
}