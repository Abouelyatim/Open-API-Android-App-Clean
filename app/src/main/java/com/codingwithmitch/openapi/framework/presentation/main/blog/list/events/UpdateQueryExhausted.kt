package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel

fun BlogViewModel.onUpdateQueryExhausted(isExhausted: Boolean) {
    state.value?.let { state ->
        this.state.value = state.copy(isQueryExhausted = isExhausted)
    }
}