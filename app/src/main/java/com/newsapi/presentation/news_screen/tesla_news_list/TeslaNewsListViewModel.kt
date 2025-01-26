package com.newsapi.presentation.news_screen.tesla_news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapi.common.Resource
import com.newsapi.domain.use_case.get_weather_news_use_case.GetWeatherNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeslaNewsListViewModel @Inject constructor(
    private val getWeatherNewsUseCase: GetWeatherNewsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<TeslaNewsListState>(TeslaNewsListState.Loading())
    val state: State<TeslaNewsListState> = _state

    init {
        getWeatherNewsCoins()
    }

    private fun getWeatherNewsCoins() {
        getWeatherNewsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TeslaNewsListState.Success(result.data)
                }

                is Resource.Loading -> {
                    _state.value = TeslaNewsListState.Loading()
                }

                is Resource.Error -> {
                    _state.value = TeslaNewsListState.Error(result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}





