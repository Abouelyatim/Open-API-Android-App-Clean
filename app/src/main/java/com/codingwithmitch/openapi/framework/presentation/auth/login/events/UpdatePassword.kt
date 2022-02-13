package com.codingwithmitch.openapi.framework.presentation.auth.login.events

import com.codingwithmitch.openapi.framework.presentation.auth.login.LoginViewModel

fun LoginViewModel.onUpdatePassword(password: String){
    state.value?.let { state ->
        this.state.value = state.copy(password = password)
    }
}