package com.newsapi.presentation.news_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newsapi.presentation.news_screen.NewsViewModel
import com.newsapi.presentation.news_screen.crypto_news_list.components.CryptoNewsListScreen
import com.newsapi.presentation.news_screen.films_news_list.components.FilmsNewsListScreen
import com.newsapi.presentation.news_screen.tesla_news_list.components.WeatherNewsListScreen

@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {

    var pagerState = rememberPagerState {
        newsViewModel.tabItems.size
    }

    //связываем HorizontalPager и TabRow
    LaunchedEffect(newsViewModel.selectedTabIndex.value) {
        pagerState.animateScrollToPage(newsViewModel.selectedTabIndex.value)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            newsViewModel.selectTab(pagerState.currentPage)
        }
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        TabRow()

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            when(index) {
                0 -> WeatherNewsListScreen(navController)
                1 -> CryptoNewsListScreen(navController)
                2 -> FilmsNewsListScreen(navController)
            }
        }
    }

}


