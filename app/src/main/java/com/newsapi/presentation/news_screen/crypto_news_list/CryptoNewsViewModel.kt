package com.newsapi.presentation.news_screen.crypto_news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapi.common.Resource
import com.newsapi.domain.use_case.get_crypto_news_use_case.GetCryptoNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoNewsViewModel @Inject constructor(
    private val getCryptoNewsUseCase: GetCryptoNewsUseCase
): ViewModel() {

    private val _state = mutableStateOf<CryptoNewsListState>(CryptoNewsListState.Loading())
    val state: State<CryptoNewsListState> = _state

    init {
        getCryptoNewsCoins()
    }

    private fun getCryptoNewsCoins() {
        getCryptoNewsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CryptoNewsListState.Success(news = result.data)
                }

                is Resource.Loading -> {
                    _state.value = CryptoNewsListState.Loading(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = CryptoNewsListState.Error(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

}