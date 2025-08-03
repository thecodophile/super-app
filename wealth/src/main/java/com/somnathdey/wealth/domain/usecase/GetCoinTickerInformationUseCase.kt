package com.somnathdey.wealth.domain.usecase

import com.somnathdey.utilities.Resource
import com.somnathdey.wealth.domain.model.CoinTickerInformation
import com.somnathdey.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinTickerInformationUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke(coinId: String):Flow<Resource<CoinTickerInformation>>{
        return repository.getCoinTickerInformation(coinId)
    }
}