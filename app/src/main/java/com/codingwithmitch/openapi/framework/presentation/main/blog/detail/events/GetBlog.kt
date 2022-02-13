package com.codingwithmitch.openapi.framework.presentation.main.blog.detail.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.OnCompleteCallback
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.ViewBlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @param callback: If the blog post is successfully retrieved from cache, execute to determine if the authenticated user is the author.
 */
fun ViewBlogViewModel.getBlog(pk: Int, callback: OnCompleteCallback){
    state.value?.let { state ->
        getBlogFromCacheUseCase.getBlogFromCache(
            pk = pk
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { blog ->
                this.state.value = state.copy(blogPost = blog)
                callback.done()
            }

            dataState.stateMessage?.let { stateMessage ->
                appendToMessageQueue(stateMessage)
            }
        }.launchIn(viewModelScope)
    }
}