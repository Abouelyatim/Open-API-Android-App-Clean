package com.codingwithmitch.openapi.framework.datasource.cache.account

import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.framework.datasource.cache.account.database.AccountDao
import com.codingwithmitch.openapi.framework.datasource.cache.account.model.AccountEntity
import com.codingwithmitch.openapi.framework.datasource.cache.account.model.toEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountDaoServiceImpl
@Inject
constructor(
    private val accountDao: AccountDao
): AccountDaoService {
    override suspend fun searchByEmail(email: String): AccountEntity? {
        return accountDao.searchByEmail(email)
    }

    override suspend fun searchByPk(pk: Int): AccountEntity? {
        return accountDao.searchByPk(pk)
    }

    override suspend fun insertAndReplace(account: Account): Long {
        return accountDao.insertAndReplace(account.toEntity())
    }

    override suspend fun insertOrIgnore(account: Account): Long {
        return accountDao.insertOrIgnore(account.toEntity())
    }

    override suspend fun updateAccount(pk: Int, email: String, username: String) {
        return accountDao.updateAccount(
            pk,
            email,
            username
        )
    }
}
