package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel

fun BlogViewModel.onUpdateQuery(query: String) {
    state.value = state.value?.copy(query = query)
}