package com.newsapi.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    object News : BottomNavItem(
        title = "News",
        selectedIcon = Icons.Filled.Newspaper,
        unselectedIcon = Icons.Default.Newspaper,
        route = "news"
    )

    object Bookmarks : BottomNavItem(
        title = "Bookmarks",
        selectedIcon = Icons.Filled.Bookmarks,
        unselectedIcon = Icons.Outlined.Bookmarks,
        route = "bookmarks"
    )
}
