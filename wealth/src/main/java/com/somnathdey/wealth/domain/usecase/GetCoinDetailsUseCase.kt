package com.somnathdey.wealth.domain.usecase

import com.somnathdey.utilities.Resource
import com.somnathdey.wealth.domain.model.CoinDetails
import com.somnathdey.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> {
        return repository.getCoinDetails(coinId)
    }
}