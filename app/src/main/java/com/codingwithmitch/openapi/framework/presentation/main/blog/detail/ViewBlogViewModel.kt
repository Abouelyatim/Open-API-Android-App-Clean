package com.codingwithmitch.openapi.framework.presentation.main.blog.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.blog.ports.ConfirmBlogExistsOnServerUseCase
import com.codingwithmitch.openapi.business.interactors.blog.ports.DeleteBlogPostUseCase
import com.codingwithmitch.openapi.business.interactors.blog.ports.GetBlogFromCacheUseCase
import com.codingwithmitch.openapi.business.interactors.blog.ports.IsAuthorOfBlogPostUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.main.blog.detail.events.*
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val SHOULD_REFRESH = "should_refresh"

@HiltViewModel
class ViewBlogViewModel
@Inject
constructor(
    val sessionManager: SessionManager,
    val getBlogFromCacheUseCase: GetBlogFromCacheUseCase,
    val confirmBlogExistsOnServerUseCase: ConfirmBlogExistsOnServerUseCase,
    val isAuthorOfBlogPostUseCase: IsAuthorOfBlogPostUseCase,
    val deleteBlogPostUseCase: DeleteBlogPostUseCase,
    val savedStateHandle: SavedStateHandle,
): BaseViewModel() {

    val state: MutableLiveData<ViewBlogState> = MutableLiveData(ViewBlogState())

    init {
        savedStateHandle.get<Int>("blogPostPk")?.let { blogPostPk ->
            onTriggerEvent(ViewBlogEvents.GetBlog(blogPostPk))
        }
    }

    override fun onTriggerEvent(event: BaseEvents){
        when(event){
            is ViewBlogEvents.GetBlog -> {
                getBlog(
                    event.pk,
                    object: OnCompleteCallback { // Determine if blog exists on server
                        override fun done() {
                            state.value?.let { state ->
                                state.blogPost?.let { blog ->
                                    onTriggerEvent(ViewBlogEvents.ConfirmBlogExistsOnServer(pk = event.pk, blog.slug))
                                }
                            }
                        }
                    }
                )
            }
            is ViewBlogEvents.ConfirmBlogExistsOnServer -> {
                confirmBlogExistsOnServer(
                    event.pk,
                    event.slug,
                    object: OnCompleteCallback { // Determine if they are the author
                        override fun done() {
                            state.value?.let { state ->
                                state.blogPost?.let { blog ->
                                    onTriggerEvent(ViewBlogEvents.IsAuthor(slug = blog.slug))
                                }
                            }
                        }
                    }
                )
            }
            is ViewBlogEvents.Refresh ->{
                refresh()
            }
            is ViewBlogEvents.IsAuthor -> {
                isAuthor(event.slug)
            }
            is ViewBlogEvents.DeleteBlog -> {
                deleteBlog()
            }
            is ViewBlogEvents.OnDeleteComplete ->{
                onDeleteComplete()
            }
            is ViewBlogEvents.Error -> {
                appendToMessageQueue(event.stateMessage)
            }
            is ViewBlogEvents.OnRemoveHeadFromQueue ->{
                removeHeadFromQueue()
            }
        }
    }

    override fun removeHeadFromQueue(){
        state.value?.let { state ->
            try {
                val queue = state.queue
                queue.remove() // can throw exception if empty
                this.state.value = state.copy(queue = queue)
            }catch (e: Exception){
                Log.d(TAG, "removeHeadFromQueue: Nothing to remove from DialogQueue")
            }
        }
    }

    override fun appendToMessageQueue(stateMessage: StateMessage){
        state.value?.let { state ->
            val queue = state.queue
            if(!stateMessage.doesMessageAlreadyExistInQueue(queue = queue)){
                if(!(stateMessage.response.uiComponentType is UIComponentType.None)){
                    queue.add(stateMessage)
                    this.state.value = state.copy(queue = queue)
                }
            }
        }
    }
}

interface OnCompleteCallback {
    fun done()
}

















