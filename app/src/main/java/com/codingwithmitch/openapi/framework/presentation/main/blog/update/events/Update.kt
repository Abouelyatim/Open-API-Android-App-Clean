package com.codingwithmitch.openapi.framework.presentation.main.blog.update.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.SuccessHandling
import com.codingwithmitch.openapi.framework.presentation.main.blog.update.UpdateBlogEvents
import com.codingwithmitch.openapi.framework.presentation.main.blog.update.UpdateBlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun UpdateBlogViewModel.update(){
    state.value?.let { state ->
        state.blogPost?.let { blogPost ->
            val title = RequestBody.create(
                MediaType.parse("text/plain"),
                blogPost.title
            )
            val body = RequestBody.create(
                MediaType.parse("text/plain"),
                blogPost.body
            )
            var multipartBody: MultipartBody.Part? = null
            if(state.newImageUri != null){
                state.newImageUri.path?.let { filePath ->
                    val imageFile = File(filePath)
                    if(imageFile.exists()){
                        val requestBody =
                            RequestBody.create(
                                MediaType.parse("image/*"),
                                imageFile
                            )
                        multipartBody = MultipartBody.Part.createFormData(
                            "image",
                            imageFile.name,
                            requestBody
                        )
                    }
                }
            }
            updateBlogPostUseCase.updateBlogPost(
                authToken = sessionManager.state.value?.authToken,
                slug = state.blogPost.slug,
                title = title,
                body = body,
                image = multipartBody,
            ).onEach { dataState ->
                this.state.value = state.copy(isLoading = dataState.isLoading)

                dataState.data?.let { response ->
                    if(response.message == SuccessHandling.SUCCESS_BLOG_UPDATED){
                        onTriggerEvent(UpdateBlogEvents.OnUpdateComplete)
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