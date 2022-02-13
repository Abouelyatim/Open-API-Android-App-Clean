package com.codingwithmitch.openapi.business.datasource.cache.account

import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.framework.datasource.cache.account.AccountDaoService
import com.codingwithmitch.openapi.framework.datasource.cache.account.model.AccountEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountCacheDataSourceImpl
@Inject
constructor(
    private val accountDaoService: AccountDaoService
): AccountCacheDataSource {
    override suspend fun searchByEmail(email: String): AccountEntity? {
        return accountDaoService.searchByEmail(email)
    }

    override suspend fun searchByPk(pk: Int): AccountEntity? {
        return accountDaoService.searchByPk(pk)
    }

    override suspend fun insertAndReplace(account: Account): Long {
        return accountDaoService.insertAndReplace(account)
    }

    override suspend fun insertOrIgnore(account: Account): Long {
        return accountDaoService.insertOrIgnore(account)
    }

    override suspend fun updateAccount(pk: Int, email: String, username: String) {
        return accountDaoService.updateAccount(
            pk,
            email,
            username
        )
    }
}