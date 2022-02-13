package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogEvents
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun BlogViewModel.getOrderAndFilter() {
    state.value?.let { state ->
        getOrderAndFilterUseCase.getOrderAndFilter().onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { orderAndFilter ->
                val order = orderAndFilter.order
                val filter = orderAndFilter.filter
                this.state.value = state.copy(
                    order = order,
                    filter = filter
                )
                onTriggerEvent(BlogEvents.NewSearch)
            }

            dataState.stateMessage?.let { stateMessage ->
                appendToMessageQueue(stateMessage)
            }
        }.launchIn(viewModelScope)
    }
}