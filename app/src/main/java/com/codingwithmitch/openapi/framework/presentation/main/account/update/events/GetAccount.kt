package com.codingwithmitch.openapi.framework.presentation.main.account.update.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.main.account.update.UpdateAccountViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun UpdateAccountViewModel.getAccount(pk: Int) {
    state.value?.let { state ->
        getAccountFromCacheUseCase.getAccountFromCache(
            pk = pk,
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