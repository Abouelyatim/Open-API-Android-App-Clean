package com.codingwithmitch.openapi.business.interactors.blog.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface ConfirmBlogExistsOnServerUseCase {

    fun confirmBlogExistsOnServer(
        authToken: AuthToken?,
        pk: Int,
        slug: String,
    ): Flow<DataState<Response>>
}