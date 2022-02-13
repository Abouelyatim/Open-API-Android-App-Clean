package com.codingwithmitch.openapi.framework.presentation.main.account.password.events

import com.codingwithmitch.openapi.framework.presentation.main.account.password.AccountPasswordViewModel

fun AccountPasswordViewModel.onUpdateNewPassword(newPassword: String) {
    state.value?.let { state ->
        this.state.value = state.copy(newPassword = newPassword)
    }
}