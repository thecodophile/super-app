package com.somnathdey.datasource.di

import android.content.Context
import com.somnathdey.datasource.local.LocalDataSource
import com.somnathdey.datasource.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun providesLocalDataSource(@ApplicationContext context: Context): LocalDataSource {
        return LocalDataSourceImpl(context)
    }
}