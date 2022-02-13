package com.codingwithmitch.openapi.business.interactors.blog.ports

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogFilterOptions
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogOrderOptions
import kotlinx.coroutines.flow.Flow

interface SearchBlogsUseCase {

    fun searchBlogs(
        authToken: AuthToken?,
        query: String,
        page: Int,
        filter: BlogFilterOptions,
        order: BlogOrderOptions,
    ): Flow<DataState<List<BlogPost>>>
}