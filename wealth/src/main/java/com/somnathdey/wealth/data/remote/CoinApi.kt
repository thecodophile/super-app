package com.somnathdey.wealth.data.remote

import com.somnathdey.wealth.data.remote.dto.CoinDetailsDto
import com.somnathdey.wealth.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinDto>

    @GET("/v1/coins/{coin_id}")
    suspend fun getCoinDetails(@Path("coin_id") coinId: String): CoinDetailsDto
}

