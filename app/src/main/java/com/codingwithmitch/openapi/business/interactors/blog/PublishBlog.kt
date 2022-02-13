package com.codingwithmitch.openapi.business.interactors.blog

import com.codingwithmitch.openapi.api.handleUseCaseException
import com.codingwithmitch.openapi.business.datasource.cache.blog.BlogCacheDataSource
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSource
import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.util.*
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.ERROR_AUTH_TOKEN_INVALID
import com.codingwithmitch.openapi.business.interactors.blog.ports.PublishBlogUseCase
import com.codingwithmitch.openapi.framework.datasource.network.main.model.toBlogPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PublishBlog(
    private val service: MainNetworkDataSource,
    private val cache: BlogCacheDataSource,
) : PublishBlogUseCase {
    private val TAG: String = "AppDebug"

    override fun publishBlog(
        authToken: AuthToken?,
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?,
    ): Flow<DataState<Response>> = flow {
        emit(DataState.loading<Response>())
        if(authToken == null){
            throw Exception(ERROR_AUTH_TOKEN_INVALID)
        }
        // attempt update
        val createResponse = service.createBlog(
            "Token ${authToken.token}",
            title,
            body,
            image
        )

        // If they don't have a paid membership account it will still return a 200 with failure message
        // Need to account for that
        if (createResponse.response.equals(SuccessHandling.RESPONSE_MUST_BECOME_CODINGWITHMITCH_MEMBER)) { // failure
            throw Exception(SuccessHandling.RESPONSE_MUST_BECOME_CODINGWITHMITCH_MEMBER)
        }

        if(createResponse.response == ErrorHandling.GENERIC_ERROR){
            throw Exception(createResponse.errorMessage)
        }

        // insert the new blog into the cache
        cache.insert(createResponse.toBlogPost())

        // Tell the UI it was successful
        emit(DataState.data<Response>(
            data = Response(
                message = SuccessHandling.SUCCESS_BLOG_CREATED,
                uiComponentType = UIComponentType.None(),
                messageType = MessageType.Success()
            ),
            response = null,
        ))
    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}






















