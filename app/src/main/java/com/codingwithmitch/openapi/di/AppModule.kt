package com.codingwithmitch.openapi.di

import android.app.Application
import androidx.room.Room
import com.codingwithmitch.openapi.business.datasource.cache.account.AccountCacheDataSource
import com.codingwithmitch.openapi.business.datasource.cache.account.AccountCacheDataSourceImpl
import com.codingwithmitch.openapi.business.datasource.cache.auth.AuthCacheDataSource
import com.codingwithmitch.openapi.business.datasource.cache.auth.AuthCacheDataSourceImpl
import com.codingwithmitch.openapi.business.datasource.cache.blog.BlogCacheDataSource
import com.codingwithmitch.openapi.business.datasource.cache.blog.BlogCacheDataSourceImpl
import com.codingwithmitch.openapi.business.datasource.datastore.AppDataStoreSource
import com.codingwithmitch.openapi.business.datasource.datastore.AppDataStoreSourceImpl
import com.codingwithmitch.openapi.business.datasource.network.auth.AuthNetworkDataSource
import com.codingwithmitch.openapi.business.datasource.network.auth.AuthNetworkDataSourceImpl
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSource
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSourceImpl
import com.codingwithmitch.openapi.business.domain.util.Constants
import com.codingwithmitch.openapi.framework.datasource.cache.AppDatabase
import com.codingwithmitch.openapi.framework.datasource.cache.AppDatabase.Companion.DATABASE_NAME
import com.codingwithmitch.openapi.framework.datasource.cache.account.AccountDaoService
import com.codingwithmitch.openapi.framework.datasource.cache.account.AccountDaoServiceImpl
import com.codingwithmitch.openapi.framework.datasource.cache.account.database.AccountDao
import com.codingwithmitch.openapi.framework.datasource.cache.auth.AuthDaoService
import com.codingwithmitch.openapi.framework.datasource.cache.auth.AuthDaoServiceImpl
import com.codingwithmitch.openapi.framework.datasource.cache.auth.database.AuthTokenDao
import com.codingwithmitch.openapi.framework.datasource.cache.blog.BlogDaoService
import com.codingwithmitch.openapi.framework.datasource.cache.blog.BlogDaoServiceImpl
import com.codingwithmitch.openapi.framework.datasource.cache.blog.database.BlogPostDao
import com.codingwithmitch.openapi.framework.datasource.datastore.AppDataStoreService
import com.codingwithmitch.openapi.framework.datasource.datastore.AppDataStoreServiceManager
import com.codingwithmitch.openapi.framework.datasource.network.auth.AuthNetworkService
import com.codingwithmitch.openapi.framework.datasource.network.auth.AuthNetworkServiceImpl
import com.codingwithmitch.openapi.framework.datasource.network.auth.api.OpenApiAuthService
import com.codingwithmitch.openapi.framework.datasource.network.main.MainNetworkService
import com.codingwithmitch.openapi.framework.datasource.network.main.MainNetworkServiceImpl
import com.codingwithmitch.openapi.framework.datasource.network.main.api.OpenApiMainService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun provideDataStoreManager(
        application: Application
    ): AppDataStoreService {
        return AppDataStoreServiceManager(application)
    }

    @Singleton
    @Provides
    fun provideAppDataStoreSource(
        appDataStore: AppDataStoreService
    ): AppDataStoreSource {
        return AppDataStoreSourceImpl(appDataStore)
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder:  Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthTokenDao(db: AppDatabase): AuthTokenDao {
        return db.getAuthTokenDao()
    }

    @Singleton
    @Provides
    fun provideAccountPropertiesDao(db: AppDatabase): AccountDao {
        return db.getAccountPropertiesDao()
    }

    @Singleton
    @Provides
    fun provideOpenApiMainService(retrofitBuilder: Retrofit.Builder): OpenApiMainService {
        return retrofitBuilder
            .build()
            .create(OpenApiMainService::class.java)
    }

    @Singleton
    @Provides
    fun provideBlogPostDao(db: AppDatabase): BlogPostDao {
        return db.getBlogPostDao()
    }

    @Singleton
    @Provides
    fun provideAccountDaoService(
        accountDao: AccountDao
    ): AccountDaoService {
        return AccountDaoServiceImpl(accountDao)
    }

    @Singleton
    @Provides
    fun provideAccountCacheDataSource(
        accountDaoService: AccountDaoService
    ): AccountCacheDataSource {
        return AccountCacheDataSourceImpl(accountDaoService)
    }

    @Singleton
    @Provides
    fun provideAuthDaoService(
        authTokenDao: AuthTokenDao
    ): AuthDaoService {
        return AuthDaoServiceImpl(authTokenDao)
    }

    @Singleton
    @Provides
    fun provideAuthCacheDataSource(
        authDaoService: AuthDaoService
    ): AuthCacheDataSource {
        return AuthCacheDataSourceImpl(authDaoService)
    }

    @Singleton
    @Provides
    fun provideBlogDaoService(
        blogPostDao: BlogPostDao
    ): BlogDaoService {
        return BlogDaoServiceImpl(blogPostDao)
    }

    @Singleton
    @Provides
    fun provideBlogCacheDataSource(
        blogDaoService: BlogDaoService
    ): BlogCacheDataSource {
        return BlogCacheDataSourceImpl(blogDaoService)
    }

    @Singleton
    @Provides
    fun provideAuthNetworkService(
        openApiAuthService: OpenApiAuthService
    ): AuthNetworkService {
        return AuthNetworkServiceImpl(openApiAuthService)
    }

    @Singleton
    @Provides
    fun provideAuthNetworkDataSource(
        authNetworkService: AuthNetworkService
    ): AuthNetworkDataSource {
        return AuthNetworkDataSourceImpl(authNetworkService)
    }

    @Singleton
    @Provides
    fun provideMainNetworkService(
        openApiMainService: OpenApiMainService
    ): MainNetworkService {
        return MainNetworkServiceImpl(openApiMainService)
    }

    @Singleton
    @Provides
    fun provideMainNetworkDataSource(
        mainNetworkService: MainNetworkService
    ): MainNetworkDataSource {
        return MainNetworkDataSourceImpl(mainNetworkService)
    }
}