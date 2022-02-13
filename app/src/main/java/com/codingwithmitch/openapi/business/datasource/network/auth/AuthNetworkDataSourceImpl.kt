package com.codingwithmitch.openapi.business.datasource.network.auth

import com.codingwithmitch.openapi.framework.datasource.network.auth.AuthNetworkService
import com.codingwithmitch.openapi.framework.datasource.network.auth.model.LoginResponse
import com.codingwithmitch.openapi.framework.datasource.network.auth.model.RegistrationResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthNetworkDataSourceImpl
@Inject
constructor(
    private val authNetworkService: AuthNetworkService
): AuthNetworkDataSource {

    override suspend fun login(email: String, password: String): LoginResponse {
        return authNetworkService.login(email, password)
    }

    override suspend fun register(
        email: String,
        username: String,
        password: String,
        password2: String
    ): RegistrationResponse {
        return authNetworkService.register(email, username, password, password2)
    }
}