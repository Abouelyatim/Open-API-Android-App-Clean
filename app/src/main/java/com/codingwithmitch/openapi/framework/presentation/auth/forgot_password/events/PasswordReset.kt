package com.codingwithmitch.openapi.framework.presentation.auth.forgot_password.events

import com.codingwithmitch.openapi.framework.presentation.auth.forgot_password.ForgotPasswordViewModel

fun ForgotPasswordViewModel.onPasswordResetSent(){
    state.value?.let { state ->
        this.state.value = state.copy(isPasswordResetLinkSent = true)
    }
}

