package com.codingwithmitch.openapi.framework.presentation.main.blog.detail.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.ViewBlogViewModel

fun ViewBlogViewModel.onDeleteComplete() {
    state.value?.let { state ->
        this.state.value = state.copy(isDeleteComplete = true)
    }
}