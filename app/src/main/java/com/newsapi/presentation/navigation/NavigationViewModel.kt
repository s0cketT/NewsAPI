package com.newsapi.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(): ViewModel() {

    val items = listOf(
        BottomNavItem.News,
        BottomNavItem.Bookmarks,
    )

    // Хранит текущий маршрут
    private val _currentRoute = MutableStateFlow<String?>(null)
    val currentRoute: StateFlow<String?> = _currentRoute

    fun observeCurrentRoute(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            _currentRoute.update { destination.route }
        }
    }

}