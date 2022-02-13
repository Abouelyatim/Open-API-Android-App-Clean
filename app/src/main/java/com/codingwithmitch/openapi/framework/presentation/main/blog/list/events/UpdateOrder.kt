package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogOrderOptions
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel

fun BlogViewModel.onUpdateOrder(order: BlogOrderOptions) {
    state.value?.let { state ->
        this.state.value = state.copy(order = order)
        saveFilterOptions(state.filter.value, order.value)
    }
}