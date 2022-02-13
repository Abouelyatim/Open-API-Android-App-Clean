package com.codingwithmitch.openapi.framework.presentation.main.blog.update.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.main.blog.update.UpdateBlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun UpdateBlogViewModel.getBlog(pk: Int){
    state.value?.let { state ->
        getBlogFromCacheUseCase.getBlogFromCache(
            pk = pk
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { blogPost ->
                this.state.value = state.copy(blogPost = blogPost)
            }

            dataState.stateMessage?.let { stateMessage ->
                appendToMessageQueue(stateMessage)
            }
        }.launchIn(viewModelScope)
    }
}