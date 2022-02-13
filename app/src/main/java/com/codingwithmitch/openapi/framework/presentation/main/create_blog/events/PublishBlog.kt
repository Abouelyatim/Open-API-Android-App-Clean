package com.codingwithmitch.openapi.framework.presentation.main.create_blog.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.business.domain.util.*
import com.codingwithmitch.openapi.framework.presentation.main.create_blog.CreateBlogEvents
import com.codingwithmitch.openapi.framework.presentation.main.create_blog.CreateBlogViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun CreateBlogViewModel.publishBlogAction(){
    state.value?.let { state ->
        val title = RequestBody.create(
            MediaType.parse("text/plain"),
            state.title
        )
        val body = RequestBody.create(
            MediaType.parse("text/plain"),
            state.body
        )
        if(state.uri == null){
            onTriggerEvent(
                CreateBlogEvents.Error(
                stateMessage = StateMessage(
                    response = Response(
                        message = ErrorHandling.ERROR_MUST_SELECT_IMAGE,
                        uiComponentType = UIComponentType.Dialog(),
                        messageType = MessageType.Error()
                    )
                )
            ))
        }
        else{
            var multipartBody: MultipartBody.Part? = null
            state.uri.path?.let { filePath ->
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
            if(multipartBody != null){
                publishBlogUseCase.publishBlog(
                    authToken = sessionManager.state.value?.authToken,
                    title = title,
                    body = body,
                    image = multipartBody,
                ).onEach { dataState ->
                    this.state.value = state.copy(isLoading = dataState.isLoading)

                    dataState.data?.let { response ->
                        if(response.message == SuccessHandling.SUCCESS_BLOG_CREATED){
                            onTriggerEvent(CreateBlogEvents.OnPublishSuccess)
                        }else{
                            appendToMessageQueue(
                                stateMessage = StateMessage(response)
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
}