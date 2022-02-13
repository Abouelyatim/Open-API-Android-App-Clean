package com.codingwithmitch.openapi.framework.presentation.auth.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.auth.ports.LoginUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.auth.login.events.login
import com.codingwithmitch.openapi.framework.presentation.auth.login.events.onUpdateEmail
import com.codingwithmitch.openapi.framework.presentation.auth.login.events.onUpdatePassword
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    val loginUseCase: LoginUseCase,
    val sessionManager: SessionManager,
): BaseViewModel(){

    val state: MutableLiveData<LoginState> = MutableLiveData(LoginState())

    override fun onTriggerEvent(event: BaseEvents){
        when(event){
            is LoginEvents.Login ->{
                login(email = event.email, password = event.password)
            }
            is LoginEvents.OnUpdateEmail ->{
                onUpdateEmail(event.email)
            }
            is LoginEvents.OnUpdatePassword ->{
                onUpdatePassword(event.password)
            }
            is LoginEvents.OnRemoveHeadFromQueue ->{
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





































