package com.codingwithmitch.openapi.framework.presentation.auth.login

import com.codingwithmitch.openapi.framework.presentation.BaseEvents

sealed class LoginEvents: BaseEvents() {

    data class Login(
        val email: String,
        val password: String
    ): LoginEvents()

    data class OnUpdateEmail(
        val email: String
    ): LoginEvents()

    data class OnUpdatePassword(
        val password: String
    ): LoginEvents()

    object OnRemoveHeadFromQueue: LoginEvents()
}
