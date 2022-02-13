package com.codingwithmitch.openapi.framework.presentation.main.account.update.events

import com.codingwithmitch.openapi.framework.presentation.main.account.update.UpdateAccountViewModel

fun UpdateAccountViewModel.onUpdateEmail(email: String){
    state.value?.let { state ->
        state.account?.let { account ->
            val new = account.copy(email = email)
            this.state.value = state.copy(account = new)
        }
    }
}