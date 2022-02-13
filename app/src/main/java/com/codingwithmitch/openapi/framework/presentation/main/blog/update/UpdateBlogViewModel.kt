package com.codingwithmitch.openapi.framework.presentation.main.blog.update

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.blog.ports.GetBlogFromCacheUseCase
import com.codingwithmitch.openapi.business.interactors.blog.ports.UpdateBlogPostUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.main.blog.update.events.*
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateBlogViewModel
@Inject
constructor(
    val sessionManager: SessionManager,
    val getBlogFromCacheUseCase: GetBlogFromCacheUseCase,
    val updateBlogPostUseCase: UpdateBlogPostUseCase,
    val savedStateHandle: SavedStateHandle,
): BaseViewModel() {

    val state: MutableLiveData<UpdateBlogState> = MutableLiveData(UpdateBlogState())

    init {
        savedStateHandle.get<Int>("blogPostPk")?.let { blogPostPk ->
            onTriggerEvent(UpdateBlogEvents.getBlog(blogPostPk))
        }
    }

    override fun onTriggerEvent(event: BaseEvents) {
        when(event){
            is UpdateBlogEvents.getBlog -> {
                getBlog(event.pk,)
            }
            is UpdateBlogEvents.OnUpdateUri -> {
                onUpdateImageUri(event.uri)
            }
            is UpdateBlogEvents.OnUpdateTitle -> {
                onUpdateTitle(event.title)
            }
            is UpdateBlogEvents.OnUpdateBody -> {
                onUpdateBody(event.body)
            }
            is UpdateBlogEvents.Update -> {
                update()
            }
            is UpdateBlogEvents.OnUpdateComplete ->{
                onUpdateComplete()
            }
            is UpdateBlogEvents.Error -> {
                appendToMessageQueue(event.stateMessage)
            }
            is UpdateBlogEvents.OnRemoveHeadFromQueue ->{
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


















