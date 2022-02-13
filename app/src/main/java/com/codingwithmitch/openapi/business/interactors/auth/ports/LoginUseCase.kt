package com.codingwithmitch.openapi.business.interactors.auth.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {

    fun login(
        email: String,
        password: String,
    ): Flow<DataState<AuthToken>>
}