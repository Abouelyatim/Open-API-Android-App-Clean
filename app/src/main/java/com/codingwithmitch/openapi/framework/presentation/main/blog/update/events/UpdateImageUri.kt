package com.codingwithmitch.openapi.framework.presentation.main.blog.update.events

import android.net.Uri
import com.codingwithmitch.openapi.framework.presentation.main.blog.update.UpdateBlogViewModel

fun UpdateBlogViewModel.onUpdateImageUri(uri: Uri){
    state.value?.let { state ->
        this.state.value = state.copy(newImageUri = uri)
    }
}