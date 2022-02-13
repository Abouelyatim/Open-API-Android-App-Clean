package com.codingwithmitch.openapi.framework.datasource.network.auth

import com.codingwithmitch.openapi.framework.datasource.network.auth.api.OpenApiAuthService
import com.codingwithmitch.openapi.framework.datasource.network.auth.model.LoginResponse
import com.codingwithmitch.openapi.framework.datasource.network.auth.model.RegistrationResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthNetworkServiceImpl
@Inject
constructor(
    private val openApiAuthService: OpenApiAuthService
): AuthNetworkService {

    override suspend fun login(email: String, password: String): LoginResponse {
        return openApiAuthService.login(email, password)
    }

    override suspend fun register(
        email: String,
        username: String,
        password: String,
        password2: String
    ): RegistrationResponse {
        return openApiAuthService.register(email, username, password, password2)
    }
}