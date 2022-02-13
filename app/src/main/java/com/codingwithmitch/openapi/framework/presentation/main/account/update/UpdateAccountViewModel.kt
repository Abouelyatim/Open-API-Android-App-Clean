package com.codingwithmitch.openapi.framework.presentation.main.account.update

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.account.ports.GetAccountFromCacheUseCase
import com.codingwithmitch.openapi.business.interactors.account.ports.UpdateAccountUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.main.account.update.events.*
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateAccountViewModel
@Inject
constructor(
    val sessionManager: SessionManager,
    val updateAccountUseCase: UpdateAccountUseCase,
    val getAccountFromCacheUseCase: GetAccountFromCacheUseCase,
    val savedStateHandle: SavedStateHandle,
): BaseViewModel(){

    val state: MutableLiveData<UpdateAccountState> = MutableLiveData(UpdateAccountState())

    init {
        savedStateHandle.get<Int>("accountPk")?.let { accountPk ->
            onTriggerEvent(UpdateAccountEvents.GetAccountFromCache(accountPk))
        }
    }

    override fun onTriggerEvent(event: BaseEvents){
        when(event){
            is UpdateAccountEvents.OnUpdateEmail -> {
                onUpdateEmail(event.email)
            }
            is UpdateAccountEvents.OnUpdateUsername -> {
                onUpdateUsername(event.username)
            }
            is UpdateAccountEvents.GetAccountFromCache -> {
                getAccount(event.pk)
            }
            is UpdateAccountEvents.Update -> {
                update(
                    email = event.email,
                    username = event.username
                )
            }
            is UpdateAccountEvents.OnRemoveHeadFromQueue -> {
                removeHeadFromQueue()
            }
            is UpdateAccountEvents.OnUpdateComplete -> {
                onUpdateComplete()
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




















