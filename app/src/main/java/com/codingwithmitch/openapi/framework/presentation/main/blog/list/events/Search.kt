package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.business.domain.util.ErrorHandling
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun BlogViewModel.search() {
    resetPage()
    clearList()
    state.value?.let { state ->
        searchBlogsUseCase.searchBlogs(
            authToken = sessionManager.state.value?.authToken,
            query = state.query,
            page = state.page,
            filter = state.filter,
            order = state.order
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { list ->
                this.state.value = state.copy(blogList = list)
            }

            dataState.stateMessage?.let { stateMessage ->
                if(stateMessage.response.message?.contains(ErrorHandling.INVALID_PAGE) == true){
                    onUpdateQueryExhausted(true)
                }else{
                    appendToMessageQueue(stateMessage)
                }
            }

        }.launchIn(viewModelScope)
    }
}