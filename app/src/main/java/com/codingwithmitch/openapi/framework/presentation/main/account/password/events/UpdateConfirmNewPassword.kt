package com.codingwithmitch.openapi.framework.presentation.main.account.password.events

import com.codingwithmitch.openapi.framework.presentation.main.account.password.AccountPasswordViewModel

fun AccountPasswordViewModel.onUpdateConfirmNewPassword(confirmNewPassword: String) {
    state.value?.let { state ->
        this.state.value = state.copy(confirmNewPassword = confirmNewPassword)
    }
}