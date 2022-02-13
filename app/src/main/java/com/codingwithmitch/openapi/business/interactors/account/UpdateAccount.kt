package com.codingwithmitch.openapi.business.interactors.account

import com.codingwithmitch.openapi.api.handleUseCaseException
import com.codingwithmitch.openapi.business.datasource.cache.account.AccountCacheDataSource
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSource
import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.*
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.GENERIC_ERROR
import com.codingwithmitch.openapi.business.domain.util.SuccessHandling.Companion.SUCCESS_ACCOUNT_UPDATED
import com.codingwithmitch.openapi.business.interactors.account.ports.UpdateAccountUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UpdateAccount(
    private val service: MainNetworkDataSource,
    private val cache: AccountCacheDataSource,
) : UpdateAccountUseCase {

    override fun updateAccount(
        authToken: AuthToken?,
        pk: Int?,
        email: String,
        username: String,
    ): Flow<DataState<Response>> = flow {
        emit(DataState.loading<Response>())
        if(authToken == null){
            throw Exception(ErrorHandling.ERROR_AUTH_TOKEN_INVALID)
        }
        if(pk == null){
            throw Exception(ErrorHandling.ERROR_PK_INVALID)
        }

        // Update network
        val response = service.updateAccount(
            authorization = "Token ${authToken.token}",
            email = email,
            username = username
        )

        if(response.response == GENERIC_ERROR){
            throw Exception(response.errorMessage)
        }
        else if(response.response != SUCCESS_ACCOUNT_UPDATED){
            throw Exception(ErrorHandling.ERROR_UPDATE_ACCOUNT)
        }

        // update cache
        cache.updateAccount(
            pk = pk,
            email = email,
            username = username
        )

        // Tell the UI it was successful
        emit(DataState.data<Response>(
            data = Response(
                message = SUCCESS_ACCOUNT_UPDATED,
                uiComponentType = UIComponentType.Toast(),
                messageType = MessageType.Success()
            ),
            response = null
        ))
    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}
