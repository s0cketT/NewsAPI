package com.newsapi.presentation.news_screen.news_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.newsapi.R
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.presentation.news_screen.news_detail.NewsDetailViewModel

@Composable
fun FountainNews(
    news: ArticleResponse,
    newsDetailViewModel: NewsDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val width = LocalConfiguration.current.screenWidthDp

    Column(modifier = Modifier.fillMaxWidth(0.9f),

        ) {
        HorizontalDivider()

        Spacer(modifier = Modifier.size((width * 0.05).dp))

        Text(text = "Source",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.W500
            )

        Spacer(modifier = Modifier.size((width * 0.03).dp))

        SuggestionChip(
            onClick = { newsDetailViewModel.openLink(news.url, context) },
            label = { Text(news.source.name,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
            ) },
            icon = {
                Icon(painter = painterResource(R.drawable.website_icon), contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size((width * 0.05).dp)
                )
            },
            shape = RoundedCornerShape(100.dp),
            colors = SuggestionChipDefaults.suggestionChipColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        )
    }
}