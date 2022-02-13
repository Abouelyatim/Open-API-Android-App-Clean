package com.codingwithmitch.openapi.framework.presentation.auth.register.events

import com.codingwithmitch.openapi.framework.presentation.auth.register.RegisterViewModel

fun RegisterViewModel.onUpdatePassword(password: String) {
    state.value?.let { state ->
        this.state.value = state.copy(password = password)
    }
}