package com.codingwithmitch.openapi.framework.presentation.main.account.update.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.SuccessHandling
import com.codingwithmitch.openapi.framework.presentation.main.account.update.UpdateAccountEvents
import com.codingwithmitch.openapi.framework.presentation.main.account.update.UpdateAccountViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun UpdateAccountViewModel.update(email: String, username: String,){
    state.value?.let { state ->
        updateAccountUseCase.updateAccount(
            authToken = sessionManager.state.value?.authToken,
            pk = sessionManager.state.value?.authToken?.accountPk,
            email = email,
            username = username,
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { response ->
                if(response.message == SuccessHandling.SUCCESS_ACCOUNT_UPDATED){
                    onTriggerEvent(UpdateAccountEvents.OnUpdateComplete)
                }else{
                    appendToMessageQueue(
                        stateMessage = StateMessage(
                            response = response
                        )
                    )
                }
            }

            dataState.stateMessage?.let { stateMessage ->
                appendToMessageQueue(stateMessage)
            }

        }.launchIn(viewModelScope)
    }
}