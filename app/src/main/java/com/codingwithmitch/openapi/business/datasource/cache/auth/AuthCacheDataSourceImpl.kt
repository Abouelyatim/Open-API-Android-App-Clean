package com.codingwithmitch.openapi.business.datasource.cache.auth

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.framework.datasource.cache.auth.AuthDaoService
import com.codingwithmitch.openapi.framework.datasource.cache.auth.model.AuthTokenEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthCacheDataSourceImpl
@Inject
constructor(
    private val authDaoService: AuthDaoService
): AuthCacheDataSource {

    override suspend fun insert(authToken: AuthToken): Long {
        return authDaoService.insert(authToken)
    }

    override suspend fun clearTokens() {
        return authDaoService.clearTokens()
    }

    override suspend fun searchByPk(pk: Int): AuthToken? {
        return authDaoService.searchByPk(pk)
    }
}