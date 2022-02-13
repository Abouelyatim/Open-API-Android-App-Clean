package com.codingwithmitch.openapi.framework.presentation.main.create_blog.events

import com.codingwithmitch.openapi.framework.presentation.main.create_blog.CreateBlogViewModel

fun CreateBlogViewModel.onUpdateBody(body: String){
    state.value?.let { state ->
        this.state.value = state.copy(body = body)
    }
}