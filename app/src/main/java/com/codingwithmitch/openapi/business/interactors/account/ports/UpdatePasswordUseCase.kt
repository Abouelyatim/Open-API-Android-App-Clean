package com.codingwithmitch.openapi.business.interactors.account.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface UpdatePasswordUseCase {

    fun updatePassword(
        authToken: AuthToken?,
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String,
    ): Flow<DataState<Response>>
}