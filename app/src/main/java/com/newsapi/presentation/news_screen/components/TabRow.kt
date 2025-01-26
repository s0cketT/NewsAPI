package com.newsapi.presentation.news_screen.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.newsapi.presentation.news_screen.NewsViewModel

@Composable
fun TabRow(
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    androidx.compose.material3.TabRow(selectedTabIndex = newsViewModel.selectedTabIndex.value) {
        newsViewModel.tabItems.forEachIndexed { index, item ->
            Tab(
                selected = index == newsViewModel.selectedTabIndex.value,
                onClick = {
                    newsViewModel.selectTab(index)
                },
                text = {
                    Text(item.title)
                },
                icon = {
                    Icon(
                        imageVector = if (index == newsViewModel.selectedTabIndex.value) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title,
                    )
                }
            )
        }
    }

}