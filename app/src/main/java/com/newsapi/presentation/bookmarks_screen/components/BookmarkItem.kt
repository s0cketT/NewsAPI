package com.newsapi.presentation.bookmarks_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newsapi.common.Constants.News_Detail_Screen
import com.newsapi.data.database.bookmark.BookmarkDB
import com.newsapi.presentation.bookmarks_screen.BookmarksViewModel

@Composable
fun BookmarkItem(
    bookmark: BookmarkDB,
    navController: NavController,
    bookmarksViewModel: BookmarksViewModel = hiltViewModel()
) {

    val width = LocalConfiguration.current.screenWidthDp

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
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val encodedJson = bookmarksViewModel.getEncodedJsonForBookmarkDetail(bookmark)

                if (encodedJson.isNotEmpty()) {
                    navController.navigate("$News_Detail_Screen/$encodedJson")
                }
            }
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = bookmark.title,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.W500
            )

            Spacer(modifier = Modifier.size((width * 0.02).dp))

            Text(
                text = bookmark.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.size((width * 0.02).dp))

            Text(
                text = "Source: ${bookmark.sourceName}",
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 10.dp)
            )
        }
    }
}

