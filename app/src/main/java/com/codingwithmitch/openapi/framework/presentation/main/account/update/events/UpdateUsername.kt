package com.codingwithmitch.openapi.framework.presentation.main.account.update.events

import com.codingwithmitch.openapi.framework.presentation.main.account.update.UpdateAccountViewModel

fun UpdateAccountViewModel.onUpdateUsername(username: String){
    state.value?.let { state ->
        state.account?.let { account ->
            val new = account.copy(username = username)
            this.state.value = state.copy(account = new)
        }
    }
}