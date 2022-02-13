package com.codingwithmitch.openapi.framework.presentation.main.blog.detail

import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.framework.presentation.BaseEvents


sealed class ViewBlogEvents : BaseEvents() {

    data class IsAuthor(val slug: String): ViewBlogEvents()

    data class GetBlog(val pk: Int): ViewBlogEvents()

    object Refresh: ViewBlogEvents()

    data class ConfirmBlogExistsOnServer(
        val pk: Int,
        val slug: String
    ): ViewBlogEvents()

    object DeleteBlog: ViewBlogEvents()

    object OnDeleteComplete: ViewBlogEvents()

    data class Error(val stateMessage: StateMessage): ViewBlogEvents()

    object OnRemoveHeadFromQueue: ViewBlogEvents()
}
