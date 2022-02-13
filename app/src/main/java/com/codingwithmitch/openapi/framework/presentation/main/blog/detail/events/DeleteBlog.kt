package com.codingwithmitch.openapi.framework.presentation.main.blog.detail.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.SuccessHandling
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.ViewBlogEvents
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.ViewBlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun ViewBlogViewModel.deleteBlog(){
    state.value?.let { state ->
        state.blogPost?.let { blogPost ->
            deleteBlogPostUseCase.deleteBlogPost(
                authToken = sessionManager.state.value?.authToken,
                blogPost = blogPost
            ).onEach { dataState ->
                this.state.value = state.copy(isLoading = dataState.isLoading)

                dataState.data?.let { response ->
                    if(response.message == SuccessHandling.SUCCESS_BLOG_DELETED){
                        onTriggerEvent(ViewBlogEvents.OnDeleteComplete)
                    }else{
                        appendToMessageQueue(
                            stateMessage = StateMessage(
                                response = response
                            )
                        )
                    }
                }

                dataState.stateMessage?.let { stateMessage ->
                    appendToMessageQueue(stateMessage)
                }
            }.launchIn(viewModelScope)
        }
    }
}