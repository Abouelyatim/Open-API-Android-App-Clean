package com.codingwithmitch.openapi.framework.datasource.cache.auth

import com.codingwithmitch.openapi.business.domain.models.AuthToken
import com.codingwithmitch.openapi.framework.datasource.cache.auth.database.AuthTokenDao
import com.codingwithmitch.openapi.framework.datasource.cache.auth.model.AuthTokenEntity
import com.codingwithmitch.openapi.framework.datasource.cache.auth.model.toAuthToken
import com.codingwithmitch.openapi.framework.datasource.cache.auth.model.toEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDaoServiceImpl
@Inject
constructor(
    private val authTokenDao: AuthTokenDao
): AuthDaoService {
    override suspend fun insert(authToken: AuthToken): Long {
        return authTokenDao.insert(authToken.toEntity())
    }

    override suspend fun clearTokens() {
        return authTokenDao.clearTokens()
    }

    override suspend fun searchByPk(pk: Int): AuthToken? {
        return authTokenDao.searchByPk(pk)?.toAuthToken()
    }
}