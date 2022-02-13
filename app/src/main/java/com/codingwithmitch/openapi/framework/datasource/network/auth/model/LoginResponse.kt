package com.codingwithmitch.openapi.framework.datasource.network.auth.model

import com.google.gson.annotations.SerializedName

class LoginResponse(

    @SerializedName("response")
    var response: String,

    @SerializedName("error_message")
    var errorMessage: String?,

    @SerializedName("token")
    var token: String,

    @SerializedName("pk")
    var pk: Int,

    @SerializedName("email")
    var email: String
)
