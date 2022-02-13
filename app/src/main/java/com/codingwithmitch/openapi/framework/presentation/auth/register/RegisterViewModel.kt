package com.codingwithmitch.openapi.framework.presentation.auth.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.auth.ports.RegisterUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.auth.register.events.*
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject
constructor(
    val registerUseCase: RegisterUseCase,
    val sessionManager: SessionManager,
) : BaseViewModel() {

    val state: MutableLiveData<RegisterState> = MutableLiveData(RegisterState())

    override fun onTriggerEvent(event: BaseEvents) {
        when (event) {
            is RegisterEvents.Register -> {
                register(
                    email = event.email,
                    username = event.username,
                    password = event.password,
                    confirmPassword = event.confirmPassword,
                )
            }
            is RegisterEvents.OnUpdateEmail -> {
                onUpdateEmail(event.email)
            }
            is RegisterEvents.OnUpdateUsername -> {
                onUpdateUsername(event.username)
            }
            is RegisterEvents.OnUpdatePassword -> {
                onUpdatePassword(event.password)
            }
            is RegisterEvents.OnUpdateConfirmPassword -> {
                onUpdateConfirmPassword(event.confirmPassword)
            }
            is RegisterEvents.OnRemoveHeadFromQueue -> {
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





































