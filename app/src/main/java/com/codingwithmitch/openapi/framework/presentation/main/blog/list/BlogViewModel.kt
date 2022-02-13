package com.codingwithmitch.openapi.framework.presentation.main.blog.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codingwithmitch.openapi.business.datasource.datastore.AppDataStoreSource
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.blog.ports.GetOrderAndFilterUseCase
import com.codingwithmitch.openapi.business.interactors.blog.ports.SearchBlogsUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.events.*
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlogViewModel
@Inject
constructor(
    val sessionManager: SessionManager,
    val searchBlogsUseCase: SearchBlogsUseCase,
    val getOrderAndFilterUseCase: GetOrderAndFilterUseCase,
    val appDataStoreSource: AppDataStoreSource,
) : BaseViewModel() {

    val state: MutableLiveData<BlogState> = MutableLiveData(BlogState())

    init {
        onTriggerEvent(BlogEvents.GetOrderAndFilter)
    }

    override fun onTriggerEvent(event: BaseEvents) {
        when (event) {
            is BlogEvents.NewSearch -> {
                search()
            }
            is BlogEvents.NextPage -> {
                nextPage()
            }
            is BlogEvents.UpdateFilter -> {
                onUpdateFilter(event.filter)
            }
            is BlogEvents.UpdateQuery -> {
                onUpdateQuery(event.query)
            }
            is BlogEvents.UpdateOrder -> {
                onUpdateOrder(event.order)
            }
            is BlogEvents.GetOrderAndFilter -> {
                getOrderAndFilter()
            }
            is BlogEvents.Error -> {
                appendToMessageQueue(event.stateMessage)
            }
            is BlogEvents.OnRemoveHeadFromQueue -> {
                removeHeadFromQueue()
            }
        }
    }

    override fun removeHeadFromQueue() {
        state.value?.let { state ->
            try {
                val queue = state.queue
                queue.remove() // can throw exception if empty
                this.state.value = state.copy(queue = queue)
            } catch (e: Exception) {
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
















