package com.somnathdey.wealth.domain.usecase

import com.somnathdey.utilities.Resource
import com.somnathdey.wealth.domain.model.Coin
import com.somnathdey.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }
}