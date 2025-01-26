package com.newsapi.presentation.news_screen.films_news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapi.common.Resource
import com.newsapi.domain.use_case.get_films_news_use_case.GetFilmsNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FilmsNewsViewModel @Inject constructor(
    private val getFilmsNewsUseCase: GetFilmsNewsUseCase
): ViewModel() {

    private val _state = mutableStateOf<FilmsNewsListState>(FilmsNewsListState.Loading())
    val state: State<FilmsNewsListState> = _state

    init {
        getFilmsNewsCoins()
    }

    private fun getFilmsNewsCoins() {
        getFilmsNewsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FilmsNewsListState.Success(news = result.data)
                }

                is Resource.Loading -> {
                    _state.value = FilmsNewsListState.Loading(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = FilmsNewsListState.Error(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

}