package com.codingwithmitch.openapi.business.interactors.account

import com.codingwithmitch.openapi.api.handleUseCaseException
import com.codingwithmitch.openapi.business.datasource.cache.account.AccountCacheDataSource
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSource
import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.ERROR_AUTH_TOKEN_INVALID
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.ERROR_UNABLE_TO_RETRIEVE_ACCOUNT_DETAILS
import com.codingwithmitch.openapi.business.interactors.account.ports.GetAccountUseCase
import com.codingwithmitch.openapi.framework.datasource.cache.account.model.toAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetAccount(
    private val service: MainNetworkDataSource,
    private val cache: AccountCacheDataSource,
) : GetAccountUseCase {
    private val TAG: String = "AppDebug"

    override fun getAccount(
        authToken: AuthToken?,
    ): Flow<DataState<Account>> = flow {
        emit(DataState.loading<Account>())
        if(authToken == null){
            throw Exception(ERROR_AUTH_TOKEN_INVALID)
        }
        // get from network
        val account = service.getAccount("Token ${authToken.token}")

        // update/insert into the cache
        cache.insertAndReplace(account)

        // emit from cache
        val cachedAccount = cache.searchByPk(account.pk)?.toAccount()

        if(cachedAccount == null){
            throw Exception(ERROR_UNABLE_TO_RETRIEVE_ACCOUNT_DETAILS)
        }

        emit(DataState.data(response = null, cachedAccount))
    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}















