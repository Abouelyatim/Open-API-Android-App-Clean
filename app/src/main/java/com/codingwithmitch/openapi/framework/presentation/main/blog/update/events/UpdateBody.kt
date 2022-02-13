package com.codingwithmitch.openapi.framework.presentation.main.blog.update.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.update.UpdateBlogViewModel

fun UpdateBlogViewModel.onUpdateBody(body: String){
    state.value?.let { state ->
        state.blogPost?.let { blogPost ->
            val curr = blogPost.copy(body = body)
            this.state.value = state.copy(blogPost = curr)
        }
    }
}