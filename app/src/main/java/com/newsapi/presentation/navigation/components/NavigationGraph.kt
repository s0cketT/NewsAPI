package com.newsapi.presentation.navigation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.newsapi.common.Constants.News_Detail_Screen
import com.newsapi.domain.model.ArticleResponse
import com.newsapi.presentation.bookmarks_screen.components.BookmarksScreen
import com.newsapi.presentation.navigation.BottomNavItem
import com.newsapi.presentation.news_screen.components.NewsScreen
import com.newsapi.presentation.news_screen.news_detail.components.NewsDetailScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.News.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.News.route) {
            NewsScreen(navController)
        }
        composable(BottomNavItem.Bookmarks.route) {
            BookmarksScreen(navController)
        }

        composable("$News_Detail_Screen/{newsJson}") { backStackEntry ->
            NewsDetailScreen(backStackEntry, navController)
        }
    }
}
