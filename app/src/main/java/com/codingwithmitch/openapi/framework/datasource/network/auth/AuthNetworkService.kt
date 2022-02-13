package com.codingwithmitch.openapi.framework.datasource.network.auth

import com.codingwithmitch.openapi.framework.datasource.network.auth.model.LoginResponse
import com.codingwithmitch.openapi.framework.datasource.network.auth.model.RegistrationResponse

interface AuthNetworkService {

    suspend fun login(email: String, password: String): LoginResponse

    suspend fun register(
        email: String,
        username: String,
        password: String,
        password2: String
    ): RegistrationResponse
}