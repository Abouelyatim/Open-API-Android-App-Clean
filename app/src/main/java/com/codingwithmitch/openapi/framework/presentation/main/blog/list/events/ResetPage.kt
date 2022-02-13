package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel

fun BlogViewModel.resetPage() {
    state.value = state.value?.copy(page = 1)
    onUpdateQueryExhausted(false)
}