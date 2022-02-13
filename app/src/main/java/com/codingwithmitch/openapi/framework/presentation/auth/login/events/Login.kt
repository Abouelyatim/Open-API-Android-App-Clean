package com.codingwithmitch.openapi.framework.presentation.auth.login.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.auth.login.LoginViewModel
import com.codingwithmitch.openapi.framework.presentation.session.SessionEvents
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun LoginViewModel.login(email: String, password: String){
    // TODO("Perform some simple form validation")
    state.value?.let { state ->
        loginUseCase.login(
            email = email,
            password = password,
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { authToken ->
                sessionManager.onTriggerEvent(SessionEvents.Login(authToken))
            }

            dataState.stateMessage?.let { stateMessage ->
                appendToMessageQueue(stateMessage)
            }
        }.launchIn(viewModelScope)
    }
}