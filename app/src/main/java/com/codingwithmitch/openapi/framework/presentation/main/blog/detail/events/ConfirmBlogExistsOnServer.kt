package com.codingwithmitch.openapi.framework.presentation.main.blog.detail.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.SuccessHandling
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.OnCompleteCallback
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.ViewBlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun ViewBlogViewModel.confirmBlogExistsOnServer(pk: Int, slug: String, callback: OnCompleteCallback){
    state.value?.let { state ->
        confirmBlogExistsOnServerUseCase.confirmBlogExistsOnServer(
            authToken = sessionManager.state.value?.authToken,
            pk = pk,
            slug = slug,
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { response ->
                if(response.message == SuccessHandling.SUCCESS_BLOG_DOES_NOT_EXIST_IN_CACHE
                    || response.message == SuccessHandling.SUCCESS_BLOG_EXISTS_ON_SERVER
                ){
                    // Blog exists in cache and on server. All is good.
                    callback.done()
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