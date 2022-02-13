package com.codingwithmitch.openapi.business.interactors.auth.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {

    fun register(
        email: String,
        username: String,
        password: String,
        confirmPassword: String,
    ): Flow<DataState<AuthToken>>
}