package com.codingwithmitch.openapi.framework.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithmitch.openapi.framework.datasource.cache.auth.database.AuthTokenDao
import com.codingwithmitch.openapi.framework.datasource.cache.account.database.AccountDao
import com.codingwithmitch.openapi.framework.datasource.cache.account.model.AccountEntity
import com.codingwithmitch.openapi.framework.datasource.cache.auth.model.AuthTokenEntity
import com.codingwithmitch.openapi.framework.datasource.cache.blog.database.BlogPostDao
import com.codingwithmitch.openapi.framework.datasource.cache.blog.model.BlogPostEntity

@Database(entities = [AuthTokenEntity::class, AccountEntity::class, BlogPostEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAuthTokenDao(): AuthTokenDao

    abstract fun getAccountPropertiesDao(): AccountDao

    abstract fun getBlogPostDao(): BlogPostDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }


}








