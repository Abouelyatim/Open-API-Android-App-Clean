package com.codingwithmitch.openapi.framework.datasource.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private const val APP_DATASTORE = "app"

class AppDataStoreServiceManager(
    val context: Application
): AppDataStoreService {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(APP_DATASTORE)

    override suspend fun setValue(
        key: String,
        value: String
    ) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun readValue(
        key: String,
    ): String? {
        return context.dataStore.data.first()[stringPreferencesKey(key)]
    }
}