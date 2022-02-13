package com.codingwithmitch.openapi.framework.datasource.cache.auth.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithmitch.openapi.framework.datasource.cache.auth.model.AuthTokenEntity

@Dao
interface AuthTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(authToken: AuthTokenEntity): Long

    @Query("DELETE FROM auth_token")
    suspend fun clearTokens()

    @Query("SELECT * FROM auth_token WHERE account_pk = :pk")
    suspend fun searchByPk(pk: Int): AuthTokenEntity?
}


















