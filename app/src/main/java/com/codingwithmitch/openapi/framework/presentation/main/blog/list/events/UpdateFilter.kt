package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogFilterOptions
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel

fun BlogViewModel.onUpdateFilter(filter: BlogFilterOptions) {
    state.value?.let { state ->
        this.state.value = state.copy(filter = filter)
        saveFilterOptions(filter.value, state.order.value)
    }
}