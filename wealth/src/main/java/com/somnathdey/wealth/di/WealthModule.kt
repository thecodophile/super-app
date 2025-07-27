package com.somnathdey.wealth.di

import com.somnathdey.utilities.constants.AppConstants
import com.somnathdey.wealth.data.remote.CoinApi
import com.somnathdey.wealth.data.repository.CoinRepositoryImpl
import com.somnathdey.wealth.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WealthModule {

    @Provides
    @Singleton
    fun provideCoinPaprikaAPI(): CoinApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(httpLoggingInterceptor)

        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }

        httpClient.addInterceptor(interceptor)

        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL_COIN_PAPRIKA)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(CoinApi::class.java)
    }


    @Provides
    @Singleton
    fun providesCoinRepository(api: CoinApi): CoinRepository {
        return CoinRepositoryImpl(api);
    }
}