package com.codingwithmitch.openapi.framework.presentation.main.account.detail.events

import com.codingwithmitch.openapi.framework.presentation.main.account.detail.AccountViewModel
import com.codingwithmitch.openapi.framework.presentation.session.SessionEvents

fun AccountViewModel.logout() {
    sessionManager.onTriggerEvent(SessionEvents.Logout)
}