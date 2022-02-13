package com.codingwithmitch.openapi.framework.presentation.main.blog.list

data class OrderAndFilter(
    val order: BlogOrderOptions,
    val filter: BlogFilterOptions,
)
