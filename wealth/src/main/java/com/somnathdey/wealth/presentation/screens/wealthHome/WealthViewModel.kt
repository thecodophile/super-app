package com.somnathdey.wealth.presentation.screens.wealthHome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.somnathdey.utilities.Resource
import com.somnathdey.wealth.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WealthViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WealthHomeScreenState())
    val state: State<WealthHomeScreenState> = _state

    init {
        viewModelScope.launch {
            getCoins()
        }
    }

    private suspend fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = WealthHomeScreenState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = WealthHomeScreenState(
                        coins = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = WealthHomeScreenState(
                        error = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}