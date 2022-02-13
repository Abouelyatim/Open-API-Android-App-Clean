package com.codingwithmitch.openapi.framework.presentation.auth.register

import com.codingwithmitch.openapi.framework.presentation.BaseEvents


sealed class RegisterEvents : BaseEvents(){

    data class Register(
        val email: String,
        val username: String,
        val password: String,
        val confirmPassword: String,
    ): RegisterEvents()

    data class OnUpdateEmail(
        val email: String
    ): RegisterEvents()

    data class OnUpdateUsername(
        val username: String
    ): RegisterEvents()

    data class OnUpdatePassword(
        val password: String
    ): RegisterEvents()

    data class OnUpdateConfirmPassword(
        val confirmPassword: String
    ): RegisterEvents()

    object OnRemoveHeadFromQueue: RegisterEvents()
}
