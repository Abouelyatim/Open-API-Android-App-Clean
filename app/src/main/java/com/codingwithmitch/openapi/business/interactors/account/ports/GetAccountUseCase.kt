package com.codingwithmitch.openapi.business.interactors.account.ports

import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface GetAccountUseCase {
    fun getAccount(
        authToken: AuthToken?,
    ): Flow<DataState<Account>>
}