package com.codingwithmitch.openapi.business.interactors.account.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface UpdateAccountUseCase {

    fun updateAccount(
        authToken: AuthToken?,
        pk: Int?,
        email: String,
        username: String,
    ): Flow<DataState<Response>>
}