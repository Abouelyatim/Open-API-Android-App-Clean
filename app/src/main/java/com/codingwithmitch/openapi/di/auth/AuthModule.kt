package com.codingwithmitch.openapi.di.auth

import com.codingwithmitch.openapi.business.datasource.cache.account.AccountCacheDataSource
import com.codingwithmitch.openapi.business.datasource.cache.auth.AuthCacheDataSource
import com.codingwithmitch.openapi.business.datasource.datastore.AppDataStoreSource
import com.codingwithmitch.openapi.business.datasource.network.auth.AuthNetworkDataSource
import com.codingwithmitch.openapi.business.interactors.auth.Login
import com.codingwithmitch.openapi.business.interactors.auth.Register
import com.codingwithmitch.openapi.business.interactors.auth.ports.LoginUseCase
import com.codingwithmitch.openapi.business.interactors.auth.ports.RegisterUseCase
import com.codingwithmitch.openapi.business.interactors.session.CheckPreviousAuthUser
import com.codingwithmitch.openapi.business.interactors.session.Logout
import com.codingwithmitch.openapi.business.interactors.session.ports.CheckPreviousAuthUserUseCase
import com.codingwithmitch.openapi.business.interactors.session.ports.LogoutUseCase
import com.codingwithmitch.openapi.framework.datasource.network.auth.api.OpenApiAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.FlowPreview
import retrofit2.Retrofit
import javax.inject.Singleton

@FlowPreview
@Module
@InstallIn(SingletonComponent::class)
object AuthModule{

    @Singleton
    @Provides
    fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): OpenApiAuthService {
        return retrofitBuilder
            .build()
            .create(OpenApiAuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideCheckPrevAuthUser(
        accountCacheDataSource: AccountCacheDataSource,
        authCacheDataSource: AuthCacheDataSource,
    ): CheckPreviousAuthUserUseCase {
        return CheckPreviousAuthUser(
            accountCacheDataSource,
            authCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideLogin(
        service: AuthNetworkDataSource,
        accountCacheDataSource: AccountCacheDataSource,
        authCacheDataSource: AuthCacheDataSource,
        appDataStoreSource: AppDataStoreSource,
    ): LoginUseCase {
        return Login(
            service,
            accountCacheDataSource,
            authCacheDataSource,
            appDataStoreSource
        )
    }

    @Singleton
    @Provides
    fun provideLogout(
        authCacheDataSource: AuthCacheDataSource,
    ): LogoutUseCase {
        return Logout(authCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideRegister(
        service: AuthNetworkDataSource,
        accountCacheDataSource: AccountCacheDataSource,
        authCacheDataSource: AuthCacheDataSource,
        appDataStoreSource: AppDataStoreSource,
    ): RegisterUseCase {
        return Register(
            service,
            accountCacheDataSource,
            authCacheDataSource,
            appDataStoreSource
        )
    }
}









