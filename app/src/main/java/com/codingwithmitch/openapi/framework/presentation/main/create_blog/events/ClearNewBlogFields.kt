package com.codingwithmitch.openapi.framework.presentation.main.create_blog.events

import com.codingwithmitch.openapi.framework.presentation.main.create_blog.CreateBlogViewModel

// call after successfully publishing
fun CreateBlogViewModel.clearNewBlogFields(){
    onUpdateTitle("")
    onUpdateBody("")
    onUpdateUri(null)
}