package com.codingwithmitch.openapi.di.account

import com.codingwithmitch.openapi.business.datasource.cache.account.AccountCacheDataSource
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSource
import com.codingwithmitch.openapi.business.interactors.account.GetAccount
import com.codingwithmitch.openapi.business.interactors.account.GetAccountFromCache
import com.codingwithmitch.openapi.business.interactors.account.UpdateAccount
import com.codingwithmitch.openapi.business.interactors.account.UpdatePassword
import com.codingwithmitch.openapi.business.interactors.account.ports.GetAccountFromCacheUseCase
import com.codingwithmitch.openapi.business.interactors.account.ports.GetAccountUseCase
import com.codingwithmitch.openapi.business.interactors.account.ports.UpdateAccountUseCase
import com.codingwithmitch.openapi.business.interactors.account.ports.UpdatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

    @Singleton
    @Provides
    fun provideGetAccount(
        service: MainNetworkDataSource,
        cache: AccountCacheDataSource,
    ): GetAccountUseCase {
        return GetAccount(service, cache)
    }

    @Singleton
    @Provides
    fun provideUpdateAccount(
        service: MainNetworkDataSource,
        cache: AccountCacheDataSource,
    ): UpdateAccountUseCase{
        return UpdateAccount(service, cache)
    }

    @Singleton
    @Provides
    fun provideGetAccountFromCache(
        cache: AccountCacheDataSource,
    ): GetAccountFromCacheUseCase {
        return GetAccountFromCache(cache)
    }

    @Singleton
    @Provides
    fun provideUpdatePassword(
        service: MainNetworkDataSource,
        cache: AccountCacheDataSource,
    ): UpdatePasswordUseCase {
        return UpdatePassword(service)
    }
}










