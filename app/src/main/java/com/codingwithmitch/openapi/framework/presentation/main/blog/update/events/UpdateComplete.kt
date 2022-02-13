package com.codingwithmitch.openapi.framework.presentation.main.blog.update.events

import com.codingwithmitch.openapi.framework.presentation.main.blog.update.UpdateBlogViewModel

fun UpdateBlogViewModel.onUpdateComplete(){
    state.value?.let { state ->
        this.state.value = state.copy(isUpdateComplete = true)
    }
}