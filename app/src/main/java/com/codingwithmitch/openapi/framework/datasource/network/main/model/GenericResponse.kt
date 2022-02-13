package com.codingwithmitch.openapi.framework.datasource.network.main.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenericResponse(

    @SerializedName("response")
    @Expose
    val response: String?,

    @SerializedName("error_message")
    val errorMessage: String?,
)