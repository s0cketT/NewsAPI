package com.newsapi.presentation.news_screen.news_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.presentation.news_screen.news_detail.NewsDetailViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun NewsDetail(
    news: ArticleResponse,
    newsDetailViewModel: NewsDetailViewModel = hiltViewModel()
) {

    val width = LocalConfiguration.current.screenWidthDp

    Column(modifier = Modifier
        .fillMaxWidth(0.9f)
        .padding(top = (width * 0.05).dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = news.title,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.W500
        )

        Spacer(modifier = Modifier.size((width * 0.1).dp))

        news.description?.let {
            Text(
                text = it,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.W400
            )
        }

        Spacer(modifier = Modifier.size((width * 0.05).dp))

        AsyncImage(
            model = news.urlToImage,
            contentDescription = "News Image",
            modifier = Modifier,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.size((width * 0.05).dp))

        news.content?.let {
            Text(
                text = it,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.W400
            )
        }

        Spacer(modifier = Modifier.size((width * 0.03).dp))

        Text(text = newsDetailViewModel.formatIsoDate(news.publishedAt),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.W300,
            modifier = Modifier.align(Alignment.End)
            )
    }

}
