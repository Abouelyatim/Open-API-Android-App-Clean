package com.codingwithmitch.openapi.di.blog

import com.codingwithmitch.openapi.business.datasource.cache.blog.BlogCacheDataSource
import com.codingwithmitch.openapi.business.datasource.datastore.AppDataStoreSource
import com.codingwithmitch.openapi.business.datasource.network.main.MainNetworkDataSource
import com.codingwithmitch.openapi.business.interactors.blog.*
import com.codingwithmitch.openapi.business.interactors.blog.ports.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BlogModule {

    @Singleton
    @Provides
    fun provideGetBlogFromCache(
        blogCacheDataSource: BlogCacheDataSource
    ): GetBlogFromCacheUseCase {
        return GetBlogFromCache(blogCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideIsAuthorOfBlogPost(
        service: MainNetworkDataSource
    ): IsAuthorOfBlogPostUseCase {
        return IsAuthorOfBlogPost(service)
    }

    @Singleton
    @Provides
    fun provideSearchBlogs(
        service: MainNetworkDataSource,
        blogCacheDataSource: BlogCacheDataSource,
    ): SearchBlogsUseCase{
        return SearchBlogs(service, blogCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideDeleteBlog(
        service: MainNetworkDataSource,
        blogCacheDataSource: BlogCacheDataSource,
    ): DeleteBlogPostUseCase {
        return DeleteBlogPost(service, blogCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideUpdateBlog(
        service: MainNetworkDataSource,
        blogCacheDataSource: BlogCacheDataSource,
    ): UpdateBlogPostUseCase{
        return UpdateBlogPost(service, blogCacheDataSource)
    }

    @Singleton
    @Provides
    fun providePublishBlog(
        service: MainNetworkDataSource,
        blogCacheDataSource: BlogCacheDataSource,
    ): PublishBlogUseCase{
        return PublishBlog(service, blogCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideGetOrderAndFilter(
        appDataStoreSource: AppDataStoreSource
    ): GetOrderAndFilterUseCase {
        return GetOrderAndFilter(appDataStoreSource)
    }

    @Singleton
    @Provides
    fun provideConfirmBlogExistsOnServer(
        service: MainNetworkDataSource,
        cache: BlogCacheDataSource,
    ): ConfirmBlogExistsOnServerUseCase {
        return ConfirmBlogExistsOnServer(service = service, cache = cache)
    }
}

















