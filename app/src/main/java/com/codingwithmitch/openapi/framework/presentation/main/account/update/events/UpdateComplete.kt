package com.codingwithmitch.openapi.framework.presentation.main.account.update.events

import com.codingwithmitch.openapi.framework.presentation.main.account.update.UpdateAccountViewModel

fun UpdateAccountViewModel.onUpdateComplete(){
    state.value?.let { state ->
        this.state.value = state.copy(isUpdateComplete = true)
    }
}