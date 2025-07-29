package com.somnathdey.datasource.local

import com.somnathdey.datasource.local.entity.Coin

interface LocalDataSource {

    fun insertCoinListInDatabase(coinList: List<Coin>)

    fun getCoinListFromDB(): List<Coin>

    fun deleteAllCoinsFromDBd()
}