package com.codingwithmitch.openapi.framework.presentation.main.create_blog.events

import android.net.Uri
import com.codingwithmitch.openapi.framework.presentation.main.create_blog.CreateBlogViewModel

fun CreateBlogViewModel.onUpdateUri(uri: Uri?){
    state.value?.let { state ->
        this.state.value = state.copy(uri = uri)
    }
}