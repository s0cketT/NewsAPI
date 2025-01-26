package com.newsapi.presentation.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.newsapi.presentation.navigation.NavigationViewModel

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    navigationViewModel: NavigationViewModel = hiltViewModel()
) {
    val currentRoute by navigationViewModel.currentRoute.collectAsState()

    // Подключаем ViewModel к NavController для отслеживания маршрута
    LaunchedEffect(navController) {
        navigationViewModel.observeCurrentRoute(navController)
    }

    NavigationBar {
        navigationViewModel.items.forEach { item ->
            val icon = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon
            NavigationBarItem(
                icon = { Icon(imageVector = icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}