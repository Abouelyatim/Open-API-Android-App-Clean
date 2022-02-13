package com.codingwithmitch.openapi.business.interactors.auth

import com.codingwithmitch.openapi.api.handleUseCaseException
import com.codingwithmitch.openapi.business.datasource.cache.account.AccountCacheDataSource
import com.codingwithmitch.openapi.business.datasource.cache.auth.AuthCacheDataSource
import com.codingwithmitch.openapi.business.datasource.datastore.AppDataStoreSource
import com.codingwithmitch.openapi.business.datasource.network.auth.AuthNetworkDataSource
import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.ERROR_SAVE_AUTH_TOKEN
import com.codingwithmitch.openapi.business.interactors.auth.ports.LoginUseCase
import com.codingwithmitch.openapi.framework.presentation.util.DataStoreKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class Login(
    private val service: AuthNetworkDataSource,
    private val accountCacheDataSource: AccountCacheDataSource,
    private val authCacheDataSource: AuthCacheDataSource,
    private val appDataStoreSource: AppDataStoreSource,
) : LoginUseCase {

    override fun login(
        email: String,
        password: String,
    ): Flow<DataState<AuthToken>> = flow {
        emit(DataState.loading<AuthToken>())
        val loginResponse = service.login(email, password)
        // Incorrect login credentials counts as a 200 response from server, so need to handle that
        if(loginResponse.errorMessage == ErrorHandling.INVALID_CREDENTIALS){
            throw Exception(ErrorHandling.INVALID_CREDENTIALS)
        }

        // cache the Account information (don't know the username yet)
        accountCacheDataSource.insertOrIgnore(
            Account(
                pk = loginResponse.pk,
                email = loginResponse.email,
                username = ""
            )
        )

        // cache the auth token
        val authToken = AuthToken(
            loginResponse.pk,
            loginResponse.token
        )
        val result = authCacheDataSource.insert(authToken)
        // can't proceed unless token can be cached
        if(result < 0){
            throw Exception(ERROR_SAVE_AUTH_TOKEN)
        }
        // save authenticated user to datastore for auto-login next time
        appDataStoreSource.setValue(DataStoreKeys.PREVIOUS_AUTH_USER, email)
        emit(DataState.data(data = authToken, response = null))
    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}














