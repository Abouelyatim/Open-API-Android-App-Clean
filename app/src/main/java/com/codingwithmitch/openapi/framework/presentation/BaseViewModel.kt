package com.codingwithmitch.openapi.framework.presentation

import androidx.lifecycle.ViewModel
import com.codingwithmitch.openapi.business.domain.util.StateMessage

abstract class BaseViewModel: ViewModel() {
    val TAG: String = "AppDebug"

    abstract fun onTriggerEvent(event: BaseEvents)
    abstract fun removeHeadFromQueue()
    abstract fun appendToMessageQueue(stateMessage: StateMessage)
}