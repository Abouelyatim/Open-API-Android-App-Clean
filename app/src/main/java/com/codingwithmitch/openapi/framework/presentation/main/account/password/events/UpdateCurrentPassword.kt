package com.codingwithmitch.openapi.framework.presentation.main.account.password.events

import com.codingwithmitch.openapi.framework.presentation.main.account.password.AccountPasswordViewModel

fun AccountPasswordViewModel.onUpdateCurrentPassword(currentPassword: String) {
    state.value?.let { state ->
        this.state.value = state.copy(currentPassword = currentPassword)
    }
}