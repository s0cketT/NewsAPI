package com.newsapi.presentation.news_screen.films_news_list.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.presentation.news_screen.components.NewsItem

@Composable
fun FilmsNewsList(
    filmsNews: List<ArticleResponse>,
    navController: NavController
) {

    val width = LocalConfiguration.current.screenWidthDp

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(filmsNews) { news ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                elevation = CardDefaults.elevatedCardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                NewsItem(news, navController)
                Spacer(modifier = Modifier.size((width * 0.04).dp))
            }
        }
    }

}