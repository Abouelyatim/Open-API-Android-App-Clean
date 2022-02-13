package com.codingwithmitch.openapi.framework.presentation.main.blog.detail.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.ViewBlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun ViewBlogViewModel.isAuthor(slug: String){
    state.value?.let { state ->
        isAuthorOfBlogPostUseCase.isAuthorOfBlogPost(
            authToken = sessionManager.state.value?.authToken,
            slug = slug,
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { isAuthor ->
                this.state.value = state.copy(isAuthor = isAuthor)
            }

            dataState.stateMessage?.let { stateMessage ->
                appendToMessageQueue(stateMessage)
            }
        }.launchIn(viewModelScope)
    }
}