package com.codingwithmitch.openapi.business.interactors.session

import com.codingwithmitch.openapi.business.datasource.cache.account.AccountCacheDataSource
import com.codingwithmitch.openapi.business.datasource.cache.auth.AuthCacheDataSource
import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.*
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.ERROR_NO_PREVIOUS_AUTH_USER
import com.codingwithmitch.openapi.business.interactors.session.ports.CheckPreviousAuthUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Attempt to authenticate as soon as the user launches the app.
 * If no user was authenticated in a previous session then do nothing.
 */
class CheckPreviousAuthUser(
    private val accountCacheDataSource: AccountCacheDataSource,
    private val authCacheDataSource: AuthCacheDataSource,
) : CheckPreviousAuthUserUseCase {

    override fun checkPreviousAuthUser(
        email: String,
    ): Flow<DataState<AuthToken>> = flow {
        emit(DataState.loading<AuthToken>())
        var authToken: AuthToken? = null
        val entity = accountCacheDataSource.searchByEmail(email)
        if(entity != null){
            authToken = authCacheDataSource.searchByPk(entity.pk)
            if(authToken != null){
                emit(DataState.data(response = null, data = authToken))
            }
        }
        if(authToken == null){
            throw Exception(ERROR_NO_PREVIOUS_AUTH_USER)
        }
    }.catch{ e ->
        e.printStackTrace()
        emit(returnNoPreviousAuthUser())
    }

    /**
     * If no user was previously authenticated then emit this error. The UI is waiting for it.
     */
    private fun returnNoPreviousAuthUser(): DataState<AuthToken> {
        return DataState.error<AuthToken>(
            response = Response(
                SuccessHandling.RESPONSE_CHECK_PREVIOUS_AUTH_USER_DONE,
                UIComponentType.None(),
                MessageType.Error()
            )
        )
    }
}












