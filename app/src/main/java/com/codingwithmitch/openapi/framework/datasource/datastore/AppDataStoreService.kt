package com.codingwithmitch.openapi.framework.datasource.datastore


interface AppDataStoreService {

    suspend fun setValue(
        key: String,
        value: String
    )

    suspend fun readValue(
        key: String,
    ): String?
}