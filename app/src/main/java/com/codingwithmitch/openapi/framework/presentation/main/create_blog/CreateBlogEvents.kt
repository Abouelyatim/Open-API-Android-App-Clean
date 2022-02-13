package com.codingwithmitch.openapi.framework.presentation.main.create_blog

import android.net.Uri
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.framework.presentation.BaseEvents

sealed class CreateBlogEvents : BaseEvents() {

    object PublishBlog: CreateBlogEvents()

    data class OnUpdateTitle(
        val title: String,
    ): CreateBlogEvents()

    data class OnUpdateBody(
        val body: String,
    ): CreateBlogEvents()

    data class OnUpdateUri(
        val uri: Uri,
    ): CreateBlogEvents()


    object OnPublishSuccess: CreateBlogEvents()

    data class Error(val stateMessage: StateMessage): CreateBlogEvents()

    object OnRemoveHeadFromQueue: CreateBlogEvents()
}










