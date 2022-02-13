package com.codingwithmitch.openapi.framework.presentation.main.account.detail

import com.codingwithmitch.openapi.framework.presentation.BaseEvents


sealed class AccountEvents : BaseEvents(){

    object GetAccount: AccountEvents()

    object Logout: AccountEvents()

    object OnRemoveHeadFromQueue: AccountEvents()
}
