package com.codingwithmitch.openapi.business.interactors.blog.ports

import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.business.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface GetBlogFromCacheUseCase {

    fun getBlogFromCache(
        pk: Int,
    ): Flow<DataState<BlogPost>>
}