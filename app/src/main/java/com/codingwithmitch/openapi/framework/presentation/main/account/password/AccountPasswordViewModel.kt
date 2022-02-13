package com.codingwithmitch.openapi.framework.presentation.main.account.password

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.account.ports.UpdatePasswordUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.main.account.password.events.*
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountPasswordViewModel
@Inject
constructor(
    val updatePasswordUseCase: UpdatePasswordUseCase,
    val sessionManager: SessionManager,
): BaseViewModel(){

    val state: MutableLiveData<AccountPasswordState> = MutableLiveData(AccountPasswordState())

    override fun onTriggerEvent(event: BaseEvents){
        when(event){
            is AccountPasswordEvents.ChangePassword -> {
                changePassword()
            }
            is AccountPasswordEvents.OnUpdateCurrentPassword -> {
                onUpdateCurrentPassword(event.currentPassword)
            }
            is AccountPasswordEvents.OnUpdateNewPassword -> {
                onUpdateNewPassword(event.newPassword)
            }
            is AccountPasswordEvents.OnUpdateConfirmNewPassword -> {
                onUpdateConfirmNewPassword(event.confirmNewPassword)
            }
            is AccountPasswordEvents.OnRemoveHeadFromQueue ->{
                removeHeadFromQueue()
            }
            is AccountPasswordEvents.OnPasswordChanged ->{
                onPasswordChangeComplete()
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













