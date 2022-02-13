package com.codingwithmitch.openapi.business.interactors.session.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface CheckPreviousAuthUserUseCase {

    fun  checkPreviousAuthUser(
        email: String,
    ): Flow<DataState<AuthToken>>
}