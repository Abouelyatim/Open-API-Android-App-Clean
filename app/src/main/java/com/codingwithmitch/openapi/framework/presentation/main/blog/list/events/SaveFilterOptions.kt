package com.codingwithmitch.openapi.framework.presentation.main.blog.list.events

import androidx.lifecycle.viewModelScope
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.BlogViewModel
import com.codingwithmitch.openapi.framework.presentation.util.DataStoreKeys
import kotlinx.coroutines.launch

fun BlogViewModel.saveFilterOptions(filter: String, order: String) {
    viewModelScope.launch {
        appDataStoreSource.setValue(DataStoreKeys.BLOG_FILTER, filter)
        appDataStoreSource.setValue(DataStoreKeys.BLOG_ORDER, order)
    }
}