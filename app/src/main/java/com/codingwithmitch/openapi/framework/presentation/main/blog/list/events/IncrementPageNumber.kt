package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel

fun BlogViewModel.incrementPageNumber() {
    state.value?.let { state ->
        this.state.value = state.copy(page = state.page + 1)
    }
}