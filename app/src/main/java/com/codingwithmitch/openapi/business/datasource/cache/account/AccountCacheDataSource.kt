package com.codingwithmitch.openapi.business.datasource.cache.account

import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.framework.datasource.cache.account.model.AccountEntity

interface AccountCacheDataSource {

    suspend fun searchByEmail(email: String): AccountEntity?

    suspend fun searchByPk(pk: Int): AccountEntity?

    suspend fun insertAndReplace(account: Account): Long

    suspend fun insertOrIgnore(account: Account): Long

    suspend fun updateAccount(pk: Int, email: String, username: String)
}