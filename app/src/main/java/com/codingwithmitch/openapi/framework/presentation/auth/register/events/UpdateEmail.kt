package com.codingwithmitch.openapi.framework.presentation.auth.register.events

import com.codingwithmitch.openapi.framework.presentation.auth.register.RegisterViewModel

fun RegisterViewModel.onUpdateEmail(email: String) {
    state.value?.let { state ->
        this.state.value = state.copy(email = email)
    }
}