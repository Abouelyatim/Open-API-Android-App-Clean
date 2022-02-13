package com.codingwithmitch.openapi.business.datasource.datastore

interface AppDataStoreSource {

    suspend fun setValue(
        key: String,
        value: String
    )

    suspend fun readValue(
        key: String,
    ): String?
}