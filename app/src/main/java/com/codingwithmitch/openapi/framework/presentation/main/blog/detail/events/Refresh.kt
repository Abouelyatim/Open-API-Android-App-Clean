package com.codingwithmitch.openapi.framework.presentation.main.blog.detail.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.OnCompleteCallback
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.ViewBlogViewModel

fun ViewBlogViewModel.refresh(){
    state.value?.let { state ->
        state.blogPost?.let { blogPost ->
            getBlog(
                pk = blogPost.pk,
                callback = object: OnCompleteCallback {
                    override fun done() {
                        // do nothing
                    }
                }
            )
        }
    }
}