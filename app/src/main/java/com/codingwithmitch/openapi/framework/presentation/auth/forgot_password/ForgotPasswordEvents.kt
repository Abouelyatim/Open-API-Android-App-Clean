package com.codingwithmitch.openapi.framework.presentation.auth.forgot_password

import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.framework.presentation.BaseEvents

sealed class ForgotPasswordEvents : BaseEvents(){

    object OnPasswordResetLinkSent: ForgotPasswordEvents()

    data class Error(val stateMessage: StateMessage): ForgotPasswordEvents()

    object OnRemoveHeadFromQueue: ForgotPasswordEvents()
}
