package com.codingwithmitch.openapi.business.interactors.blog.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface IsAuthorOfBlogPostUseCase {

    fun isAuthorOfBlogPost(
        authToken: AuthToken?,
        slug: String,
    ): Flow<DataState<Boolean>>
}