package com.newsapi.presentation.news_screen.films_news_list.components

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newsapi.presentation.news_screen.components.NewsItem
import com.newsapi.presentation.news_screen.films_news_list.FilmsNewsListState
import com.newsapi.presentation.news_screen.films_news_list.FilmsNewsViewModel

@Composable
fun FilmsNewsListScreen(
    navController: NavController,
    filmsNewsViewModel: FilmsNewsViewModel = hiltViewModel()
) {

    val filmsNewsList = filmsNewsViewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when(filmsNewsList) {
            is FilmsNewsListState.Error -> {
                Text(
                    text = filmsNewsList.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            is FilmsNewsListState.Loading -> CircularProgressIndicator()
            is FilmsNewsListState.Success -> FilmsNewsList(filmsNewsList.news, navController)
        }

    }
}