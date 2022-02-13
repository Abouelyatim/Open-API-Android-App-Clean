package com.codingwithmitch.openapi.framework.presentation.main.account.password

import com.codingwithmitch.openapi.framework.presentation.BaseEvents


sealed class AccountPasswordEvents : BaseEvents() {

    data class ChangePassword(
        val currentPassword: String,
        val newPassword: String,
        val confirmNewPassword: String,
    ): AccountPasswordEvents()

    data class OnUpdateCurrentPassword(
        val currentPassword: String
    ): AccountPasswordEvents()

    data class OnUpdateNewPassword(
        val newPassword: String
    ): AccountPasswordEvents()

    data class OnUpdateConfirmNewPassword(
        val confirmNewPassword: String
    ): AccountPasswordEvents()

    object OnPasswordChanged: AccountPasswordEvents()

    object OnRemoveHeadFromQueue: AccountPasswordEvents()
}
