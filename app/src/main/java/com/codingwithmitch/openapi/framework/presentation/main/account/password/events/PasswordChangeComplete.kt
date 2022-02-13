package com.codingwithmitch.openapi.framework.presentation.main.account.password.events

import com.codingwithmitch.openapi.framework.presentation.main.account.password.AccountPasswordViewModel

fun AccountPasswordViewModel.onPasswordChangeComplete(){
    state.value?.let { state ->
        this.state.value = state.copy(isPasswordChangeComplete = true)
    }
}