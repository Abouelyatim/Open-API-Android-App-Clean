package com.codingwithmitch.openapi.framework.datasource.cache.auth

import com.codingwithmitch.openapi.business.domain.models.AuthToken

interface AuthDaoService {

    suspend fun insert(authToken: AuthToken): Long

    suspend fun clearTokens()

    suspend fun searchByPk(pk: Int): AuthToken?
}