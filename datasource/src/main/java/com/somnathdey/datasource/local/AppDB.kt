package com.somnathdey.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.somnathdey.datasource.dao.CoinsDao
import com.somnathdey.datasource.local.entity.Coin

@Database(entities = [Coin::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun getCoinsDao(): CoinsDao
    companion object {
        private var instance: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDB {
            return Room.databaseBuilder(context, AppDB::class.java, TableConstants.APP_DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        fun destroyDatabase() {
            instance = null
        }
    }
}


object TableConstants {
    const val APP_DB_NAME = "SuperApp_Database"
    const val COIN_LIST = "Coin_List"
}