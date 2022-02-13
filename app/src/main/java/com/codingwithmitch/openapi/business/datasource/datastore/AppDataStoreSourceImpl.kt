package com.codingwithmitch.openapi.business.datasource.datastore

import com.codingwithmitch.openapi.framework.datasource.datastore.AppDataStoreService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataStoreSourceImpl
@Inject
constructor(
    private val appDataStore: AppDataStoreService
): AppDataStoreSource {

    override suspend fun setValue(key: String, value: String) {
        return appDataStore.setValue(key, value)
    }

    override suspend fun readValue(key: String): String? {
        return appDataStore.readValue(key)
    }
}