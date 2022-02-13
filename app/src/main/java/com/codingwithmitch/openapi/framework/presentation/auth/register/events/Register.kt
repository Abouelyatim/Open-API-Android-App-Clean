package com.codingwithmitch.openapi.framework.presentation.auth.register.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.auth.register.RegisterViewModel
import com.codingwithmitch.openapi.framework.presentation.session.SessionEvents
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun RegisterViewModel.register(
    email: String,
    username: String,
    password: String,
    confirmPassword: String
) {
    // TODO("Perform some simple form validation?")
    state.value?.let { state ->
        registerUseCase.register(
            email = email,
            username = username,
            password = password,
            confirmPassword = confirmPassword,
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