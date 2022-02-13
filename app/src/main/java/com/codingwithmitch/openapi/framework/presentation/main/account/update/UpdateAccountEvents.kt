package com.codingwithmitch.openapi.framework.presentation.main.account.update

import com.codingwithmitch.openapi.framework.presentation.BaseEvents


sealed class UpdateAccountEvents : BaseEvents(){

    data class GetAccountFromCache(
        val pk: Int
    ): UpdateAccountEvents()

    data class Update(
        val email: String,
        val username: String
    ): UpdateAccountEvents()

    data class OnUpdateEmail(
        val email: String
    ): UpdateAccountEvents()

    data class OnUpdateUsername(
        val username: String
    ): UpdateAccountEvents()

    object OnUpdateComplete: UpdateAccountEvents()

    object OnRemoveHeadFromQueue: UpdateAccountEvents()
}
