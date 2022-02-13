package com.codingwithmitch.openapi.framework.presentation.main.account.password.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.SuccessHandling
import com.codingwithmitch.openapi.framework.presentation.main.account.password.AccountPasswordEvents
import com.codingwithmitch.openapi.framework.presentation.main.account.password.AccountPasswordViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun AccountPasswordViewModel.changePassword() {
    // TODO("Should perform some simple validation client-side here")
    state.value?.let { state ->
        updatePasswordUseCase.updatePassword(
            authToken = sessionManager.state.value?.authToken,
            currentPassword = state.currentPassword,
            newPassword = state.newPassword,
            confirmNewPassword = state.confirmNewPassword
        ).onEach { dataState ->
            this.state.value = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { response ->
                if(response.message == SuccessHandling.SUCCESS_PASSWORD_UPDATED){
                    onTriggerEvent(AccountPasswordEvents.OnPasswordChanged)
                }else{
                    appendToMessageQueue( // Tell the UI it was updated
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