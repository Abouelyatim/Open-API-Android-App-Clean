package com.codingwithmitch.openapi.business.interactors.blog.ports

import com.codingwithmitch.openapi.business.domain.util.DataState
import com.codingwithmitch.openapi.framework.presentation.main.blog.list.OrderAndFilter
import kotlinx.coroutines.flow.Flow

interface GetOrderAndFilterUseCase {

    fun getOrderAndFilter(): Flow<DataState<OrderAndFilter>>
}