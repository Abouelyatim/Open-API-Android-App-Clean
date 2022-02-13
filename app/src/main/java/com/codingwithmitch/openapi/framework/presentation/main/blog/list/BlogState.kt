package com.codingwithmitch.openapi.framework.presentation.main.blog.list

import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.business.domain.util.Queue
import com.codingwithmitch.openapi.business.domain.util.StateMessage

data class BlogState(
    val isLoading: Boolean = false,
    val blogList: List<BlogPost> = listOf(),
    val query: String = "",
    val page: Int = 1,
    val isQueryExhausted: Boolean = false, // no more results available, prevent next page
    val filter: BlogFilterOptions = BlogFilterOptions.DATE_UPDATED,
    val order: BlogOrderOptions = BlogOrderOptions.DESC,
    val queue: Queue<StateMessage> = Queue(mutableListOf()),
)
