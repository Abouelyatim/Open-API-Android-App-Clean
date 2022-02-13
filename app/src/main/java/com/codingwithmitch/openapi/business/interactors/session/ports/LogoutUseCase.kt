package com.codingwithmitch.openapi.business.interactors.session.ports

import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface LogoutUseCase {

    fun logout(): Flow<DataState<Response>>
}