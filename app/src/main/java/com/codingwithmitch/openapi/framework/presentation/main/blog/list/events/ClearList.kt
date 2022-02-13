package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel

fun BlogViewModel.clearList() {
    state.value?.let { state ->
        this.state.value = state.copy(blogList = listOf())
    }
}