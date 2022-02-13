package com.codingwithmitch.openapi.framework.presentation.main.create_blog.events

import com.codingwithmitch.openapi.framework.presentation.main.create_blog.CreateBlogViewModel

fun CreateBlogViewModel.onPublishSuccess(){
    clearNewBlogFields()
    state.value?.let { state ->
        this.state.value = state.copy(onPublishSuccess = true)
    }
}