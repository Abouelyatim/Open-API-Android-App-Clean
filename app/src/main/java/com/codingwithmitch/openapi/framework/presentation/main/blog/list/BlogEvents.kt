package com.codingwithmitch.openapi.framework.presentation.main.blog.list

import com.codingwithmitch.openapi.business.domain.util.StateMessage
import com.codingwithmitch.openapi.framework.presentation.BaseEvents


sealed class BlogEvents : BaseEvents() {

    object NewSearch : BlogEvents()

    object NextPage: BlogEvents()

    data class UpdateQuery(val query: String): BlogEvents()

    data class UpdateFilter(val filter: BlogFilterOptions): BlogEvents()

    data class UpdateOrder(val order: BlogOrderOptions): BlogEvents()

    object GetOrderAndFilter: BlogEvents()

    data class Error(val stateMessage: StateMessage): BlogEvents()

    object OnRemoveHeadFromQueue: BlogEvents()
}
