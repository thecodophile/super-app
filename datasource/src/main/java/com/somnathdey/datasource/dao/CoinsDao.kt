package com.somnathdey.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.somnathdey.datasource.local.TableConstants
import com.somnathdey.datasource.local.entity.Coin

@Dao
interface CoinsDao : BaseDao<Coin> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinListInDatabase(coinList: List<Coin>)

    @Query(
        "SELECT * from ${TableConstants.COIN_LIST}"
    )
    fun getCoinListFromDB(): List<Coin>

    @Query(
        "DELETE from ${TableConstants.COIN_LIST}"
    )
    fun deleteAllCoinsFromDBd()
}