package com.somnathdey.datasource.local

import android.content.Context
import com.somnathdey.datasource.local.entity.Coin
import dagger.hilt.android.qualifiers.ApplicationContext

class LocalDataSourceImpl(
    @ApplicationContext private val context: Context
) : LocalDataSource {

    private val appDB: AppDB = AppDB.getDatabase(context)

    override fun insertCoinListInDatabase(coinList: List<Coin>) {
        appDB.getCoinsDao().insertCoinListInDatabase(coinList)
    }

    override fun getCoinListFromDB(): List<Coin> {
        return appDB.getCoinsDao().getCoinListFromDB()
    }

    override fun deleteAllCoinsFromDBd() {
        appDB.getCoinsDao().deleteAllCoinsFromDBd()
    }

}