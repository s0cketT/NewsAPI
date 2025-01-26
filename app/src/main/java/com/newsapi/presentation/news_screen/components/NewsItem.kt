package com.newsapi.presentation.news_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.newsapi.common.Constants.News_Detail_Screen
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.presentation.news_screen.NewsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsItem(
    news: ArticleResponse,
    navController: NavController,
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val width = LocalConfiguration.current.screenWidthDp

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            val encodedJson = newsViewModel.getEncodedJsonForNewsDetail(news)

            if (encodedJson.isNotEmpty()) {
                navController.navigate("$News_Detail_Screen/$encodedJson")
            }
        }
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(text = news.title,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.W500
            )

        Spacer(modifier = Modifier.size((width * 0.02).dp))

        news.description?.let {
            Text(text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.size((width * 0.02).dp))

        Text(text = "Source: ${news.source.name}",
            fontSize = 14.sp,
            fontWeight = FontWeight.W300,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.align(Alignment.End).padding(end = 10.dp)
            )
    }
}



