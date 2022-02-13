package com.codingwithmitch.openapi.business.interactors.blog

import com.codingwithmitch.openapi.api.handleUseCaseException
import com.codingwithmitch.openapi.business.datasource.cache.blog.BlogCacheDataSource
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSource
import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling.Companion.ERROR_AUTH_TOKEN_INVALID
import com.codingwithmitch.openapi.business.domain.util.MessageType
import com.codingwithmitch.openapi.business.domain.util.Response
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.interactors.blog.ports.SearchBlogsUseCase
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogFilterOptions
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogOrderOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SearchBlogs(
    private val service: MainNetworkDataSource,
    private val cache: BlogCacheDataSource,
) : SearchBlogsUseCase {

    private val TAG: String = "AppDebug"

    override fun searchBlogs(
        authToken: AuthToken?,
        query: String,
        page: Int,
        filter: BlogFilterOptions,
        order: BlogOrderOptions,
    ): Flow<DataState<List<BlogPost>>> = flow {
        emit(DataState.loading<List<BlogPost>>())
        if(authToken == null){
            throw Exception(ERROR_AUTH_TOKEN_INVALID)
        }
        // get Blogs from network
        val filterAndOrder = order.value + filter.value // Ex: -date_updated

        try{ // catch network exception
            val blogs = service.searchListBlogPosts(
                "Token ${authToken.token}",
                query = query,
                ordering = filterAndOrder,
                page = page
            )

            // Insert into cache
            for(blog in blogs){
                try{
                    cache.insert(blog)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }catch (e: Exception){
            emit(
                DataState.error<List<BlogPost>>(
                    response = Response(
                        message = "Unable to update the cache.",
                        uiComponentType = UIComponentType.None(),
                        messageType = MessageType.Error()
                    )
                )
            )
        }

        // emit from cache
        val cachedBlogs = cache.returnOrderedBlogQuery(
            query = query,
            filterAndOrder = filterAndOrder,
            page = page
        )

        emit(DataState.data(response = null, data = cachedBlogs))
    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}



















