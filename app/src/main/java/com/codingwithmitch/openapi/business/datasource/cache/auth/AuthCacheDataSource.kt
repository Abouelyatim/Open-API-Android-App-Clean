package com.codingwithmitch.openapi.business.datasource.cache.auth

import com.codingwithmitch.openapi.business.domain.models.AuthToken


interface AuthCacheDataSource {

    suspend fun insert(authToken: AuthToken): Long

    suspend fun clearTokens()

    suspend fun searchByPk(pk: Int): AuthToken?
}