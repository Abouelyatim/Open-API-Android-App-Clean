package com.codingwithmitch.openapi.datasource.datastore

import com.codingwithmitch.openapi.framework.datasource.datastore.AppDataStoreService

class AppDataStoreServiceManagerFake: AppDataStoreService {

    private val datastore: MutableMap<String, String> = mutableMapOf()

    override suspend fun setValue(key: String, value: String) {
        datastore[key] = value
    }

    override suspend fun readValue(key: String): String? {
        return datastore[key]
    }
}