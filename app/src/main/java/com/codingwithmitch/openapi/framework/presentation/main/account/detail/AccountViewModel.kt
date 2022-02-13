package com.codingwithmitch.openapi.framework.presentation.main.account.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.business.domain.util.UIComponentType
import com.codingwithmitch.openapi.business.domain.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.openapi.business.interactors.account.ports.GetAccountUseCase
import com.codingwithmitch.openapi.framework.presentation.BaseEvents
import com.codingwithmitch.openapi.framework.presentation.BaseViewModel
import com.codingwithmitch.openapi.framework.presentation.main.account.detail.events.getAccount
import com.codingwithmitch.openapi.framework.presentation.main.account.detail.events.logout
import com.codingwithmitch.openapi.framework.presentation.session.SessionEvents
import com.codingwithmitch.openapi.framework.presentation.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountViewModel
@Inject
constructor(
    val sessionManager: SessionManager,
    val getAccountUseCase: GetAccountUseCase,
) : BaseViewModel() {

    val state: MutableLiveData<AccountState> = MutableLiveData(AccountState())

    override fun onTriggerEvent(event: BaseEvents) {
        when (event) {
            is AccountEvents.GetAccount -> {
                getAccount()
            }
            is AccountEvents.Logout -> {
                logout()
            }
            is AccountEvents.OnRemoveHeadFromQueue -> {
                removeHeadFromQueue()
            }
        }
    }

    override fun removeHeadFromQueue() {
        state.value?.let { state ->
            try {
                val queue = state.queue
                queue.remove() // can throw exception if empty
                this.state.value = state.copy(queue = queue)
            } catch (e: Exception) {
                Log.d(TAG, "removeHeadFromQueue: Nothing to remove from DialogQueue")
            }
        }
    }

    override fun appendToMessageQueue(stateMessage: StateMessage){
        state.value?.let { state ->
            val queue = state.queue
            if(!stateMessage.doesMessageAlreadyExistInQueue(queue = queue)){
                if(!(stateMessage.response.uiComponentType is UIComponentType.None)){
                    queue.add(stateMessage)
                    this.state.value = state.copy(queue = queue)
                }
            }
        }
    }
}















