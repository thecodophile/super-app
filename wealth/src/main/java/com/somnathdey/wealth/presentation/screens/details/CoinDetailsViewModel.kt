package com.somnathdey.wealth.presentation.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.somnathdey.utilities.Resource
import com.somnathdey.utilities.logging.AppLogger
import com.somnathdey.wealth.domain.usecase.GetCoinDetailsUseCase
import com.somnathdey.wealth.domain.usecase.GetCoinTickerInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    private val getCoinTickerInformationUseCase: GetCoinTickerInformationUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsScreenState())
    val state: State<CoinDetailsScreenState> = _state

    fun fetchDetailsForCoin(coinId: String) {
        viewModelScope.launch {
            getCoinDetails(coinId)
        }
    }

    private suspend fun getCoinDetails(coinId: String) {
        AppLogger.d(message = "Api call function for getCoinDetails")
        getCoinDetailsUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    AppLogger.d(message = "Loading state for getCoinDetails")
                    _state.value = CoinDetailsScreenState(isLoading = true)
                }

                is Resource.Success -> {
                    AppLogger.d(message = "Success state of getCoinDetails")
                    _state.value = CoinDetailsScreenState(
                        coinDetails = result.data
                    )

                    // call second api to get coin ticker information
                    getCoinTickerInformation(coinId)
                }

                is Resource.Error -> {
                    _state.value = CoinDetailsScreenState(
                        error = result.message
                    )
                    AppLogger.d(message = "Error state of getCoinDetails--> ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getCoinTickerInformation(coinId: String) {
        AppLogger.d(message = "Api call function for getCoinTickerInformation")
        getCoinTickerInformationUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    AppLogger.d(message = "Loading state for getCoinTickerInformation")
                    _state.value = _state.value.copy(
                        isLoadingCoinTickerInformation = true
                    )
                }

                is Resource.Success -> {
                    AppLogger.d(message = "Success state of getCoinTickerInformation")
                    _state.value = _state.value.copy(
                        coinTickerInformation = result.data,
                        isLoadingCoinTickerInformation = false
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        errorFetchingCoinTickerInformation = result.message,
                        isLoadingCoinTickerInformation = false
                    )
                    AppLogger.d(message = "Error state of getCoinTickerInformation--> ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun coinPriceNumberFormat(): NumberFormat{
        return NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }
    }
}