package com.codingwithmitch.openapi.framework.presentation.main.create_blog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.blog.ports.PublishBlogUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.main.create_blog.events.*
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateBlogViewModel
@Inject
constructor(
    val publishBlogUseCase: PublishBlogUseCase,
    val sessionManager: SessionManager
): BaseViewModel() {

    val state: MutableLiveData<CreateBlogState> = MutableLiveData(CreateBlogState())

    override fun onTriggerEvent(event: BaseEvents){
        when(event){
            is CreateBlogEvents.OnUpdateTitle -> {
                onUpdateTitle(event.title)
            }
            is CreateBlogEvents.OnUpdateBody -> {
                onUpdateBody(event.body)
            }
            is CreateBlogEvents.OnUpdateUri -> {
                onUpdateUri(event.uri)
            }
            is CreateBlogEvents.PublishBlog -> {
                publishBlogAction()
            }
            is CreateBlogEvents.OnPublishSuccess -> {
                onPublishSuccess()
            }
            is CreateBlogEvents.Error -> {
                appendToMessageQueue(event.stateMessage)
            }
            is CreateBlogEvents.OnRemoveHeadFromQueue ->{
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





