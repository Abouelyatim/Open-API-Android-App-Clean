package com.codingwithmitch.openapi.framework.presentation.main.account.detail.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.main.account.detail.AccountViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun AccountViewModel.getAccount() {
    state.value?.let { state ->
        getAccountUseCase.getAccount(
            authToken = sessionManager.state.value?.authToken,
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { account ->
                this.state.value = state.copy(account = account)
            }

            dataState.stateMessage?.let { stateMessage ->
                appendToMessageQueue(stateMessage)
            }

        }.launchIn(viewModelScope)
    }
}