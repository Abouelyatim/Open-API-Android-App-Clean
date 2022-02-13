package com.codingwithmitch.openapi.business.interactors.blog.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface DeleteBlogPostUseCase {

    fun deleteBlogPost(
        authToken: AuthToken?,
        blogPost: BlogPost,
    ): Flow<DataState<Response>>
}