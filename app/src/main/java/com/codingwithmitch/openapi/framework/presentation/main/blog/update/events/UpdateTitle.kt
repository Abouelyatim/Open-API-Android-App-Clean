package com.codingwithmitch.openapi.framework.presentation.main.blog.update.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.update.UpdateBlogViewModel

fun UpdateBlogViewModel.onUpdateTitle(title: String){
    state.value?.let { state ->
        state.blogPost?.let { blogPost ->
            val curr = blogPost.copy(title = title)
            this.state.value = state.copy(blogPost = curr)
        }
    }
}