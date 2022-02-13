package com.codingwithmitch.openapi.business.datasource.network.main

import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.framework.datasource.network.main.MainNetworkService
import com.codingwithmitch.openapi.framework.datasource.network.main.model.BlogCreateUpdateResponse
import com.codingwithmitch.openapi.framework.datasource.network.main.model.GenericResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainNetworkDataSourceImpl
@Inject
constructor(
    private val mainNetworkService: MainNetworkService
): MainNetworkDataSource {
    
    override suspend fun getAccount(authorization: String): Account {
        return mainNetworkService.getAccount(authorization)
    }

    override suspend fun updateAccount(
        authorization: String,
        email: String,
        username: String
    ): GenericResponse {
        return mainNetworkService.updateAccount(authorization, email, username)
    }

    override suspend fun updatePassword(
        authorization: String,
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): GenericResponse {
        return mainNetworkService.updatePassword(
            authorization,
            currentPassword,
            newPassword,
            confirmNewPassword
        )
    }

    override suspend fun searchListBlogPosts(
        authorization: String,
        query: String,
        ordering: String,
        page: Int
    ): List<BlogPost> {
        return mainNetworkService.searchListBlogPosts(authorization, query, ordering, page)
    }

    override suspend fun isAuthorOfBlogPost(authorization: String, slug: String): GenericResponse {
        return mainNetworkService.isAuthorOfBlogPost(authorization, slug)
    }

    override suspend fun deleteBlogPost(authorization: String, slug: String): GenericResponse {
        return mainNetworkService.deleteBlogPost(authorization, slug)
    }

    override suspend fun updateBlog(
        authorization: String,
        slug: String,
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?
    ): BlogCreateUpdateResponse {
        return mainNetworkService.updateBlog(authorization, slug, title, body, image)
    }

    override suspend fun createBlog(
        authorization: String,
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?
    ): BlogCreateUpdateResponse {
        return mainNetworkService.createBlog(authorization, title, body, image)
    }

    override suspend fun getBlog(authorization: String, slug: String): BlogPost {
        return mainNetworkService.getBlog(authorization, slug)
    }
}