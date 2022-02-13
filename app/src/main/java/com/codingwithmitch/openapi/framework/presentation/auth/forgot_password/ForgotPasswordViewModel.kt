package com.codingwithmitch.openapi.framework.presentation.auth.forgot_password

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.auth.forgot_password.events.onPasswordResetSent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel
@Inject
constructor(

): BaseViewModel() {

    val state: MutableLiveData<ForgotPasswordState> = MutableLiveData(ForgotPasswordState())

    override fun onTriggerEvent(event: BaseEvents){
        when(event){
            is ForgotPasswordEvents.OnPasswordResetLinkSent -> {
                onPasswordResetSent()
            }
            is ForgotPasswordEvents.Error -> {
                appendToMessageQueue(event.stateMessage)
            }
            is ForgotPasswordEvents.OnRemoveHeadFromQueue -> {
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
















