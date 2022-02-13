package com.codingwithmitch.openapi.business.interactors.account.ports

import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.business.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface GetAccountFromCacheUseCase {
    fun getAccountFromCache(
        pk: Int,
    ): Flow<DataState<Account>>
}