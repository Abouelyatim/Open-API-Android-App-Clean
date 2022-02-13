package com.codingwithmitch.openapi.business.interactors.blog.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.Response
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PublishBlogUseCase {

    fun publishBlog(
        authToken: AuthToken?,
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?,
    ): Flow<DataState<Response>>
}