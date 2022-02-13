package com.codingwithmitch.openapi.business.interactors.blog

import com.codingwithmitch.openapi.api.handleUseCaseException
import com.codingwithmitch.openapi.business.datasource.cache.blog.BlogCacheDataSource
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSource
import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.ERROR_AUTH_TOKEN_INVALID
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.ERROR_DELETE_BLOG_DOES_NOT_EXIST
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.GENERIC_ERROR
import com.codingwithmitch.openapi.business.domain.util.MessageType
import com.codingwithmitch.openapi.business.domain.util.Response
import com.codingwithmitch.openapi.business.domain.util.SuccessHandling.Companion.SUCCESS_BLOG_DELETED
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.interactors.blog.ports.DeleteBlogPostUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DeleteBlogPost(
    private val service: MainNetworkDataSource,
    private val cache: BlogCacheDataSource,
) : DeleteBlogPostUseCase {
    /**
     * If successful this will emit a string saying: 'deleted'
     */
    override fun deleteBlogPost(
        authToken: AuthToken?,
        blogPost: BlogPost,
    ): Flow<DataState<Response>> = flow{
        emit(DataState.loading<Response>())
        if(authToken == null){
            throw Exception(ERROR_AUTH_TOKEN_INVALID)
        }
        // attempt delete from network
        val response = service.deleteBlogPost(
            "Token ${authToken.token}",
            blogPost.slug
        )
        if(response.response == GENERIC_ERROR
            && response.errorMessage != ERROR_DELETE_BLOG_DOES_NOT_EXIST){ // failure
            throw Exception(response.errorMessage)
        }else{
            // delete from cache
            cache.deleteBlogPost(blogPost)
            // Tell the UI it was successful
            emit(DataState.data<Response>(
                data = Response(
                    message = SUCCESS_BLOG_DELETED,
                    uiComponentType = UIComponentType.None(),
                    messageType = MessageType.Success()
                ),
                response = null
            ))
        }
    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}
















