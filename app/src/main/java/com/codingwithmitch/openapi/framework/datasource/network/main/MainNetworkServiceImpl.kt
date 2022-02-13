package com.codingwithmitch.openapi.framework.datasource.network.main

import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.framework.datasource.network.main.api.OpenApiMainService
import com.codingwithmitch.openapi.framework.datasource.network.main.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainNetworkServiceImpl
@Inject
constructor(
    private val openApiAuthService: OpenApiMainService
): MainNetworkService {
    
    override suspend fun getAccount(authorization: String): Account {
        return openApiAuthService.getAccount(authorization).toAccount()
    }

    override suspend fun updateAccount(
        authorization: String,
        email: String,
        username: String
    ): GenericResponse {
        return openApiAuthService.updateAccount(authorization, email, username)
    }

    override suspend fun updatePassword(
        authorization: String,
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): GenericResponse {
        return openApiAuthService.updatePassword(
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
        return openApiAuthService.searchListBlogPosts(authorization, query, ordering, page).toList()
    }

    override suspend fun isAuthorOfBlogPost(authorization: String, slug: String): GenericResponse {
        return openApiAuthService.isAuthorOfBlogPost(authorization, slug)
    }

    override suspend fun deleteBlogPost(authorization: String, slug: String): GenericResponse {
        return openApiAuthService.deleteBlogPost(authorization, slug)
    }

    override suspend fun updateBlog(
        authorization: String,
        slug: String,
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?
    ): BlogCreateUpdateResponse {
        return openApiAuthService.updateBlog(authorization, slug, title, body, image)
    }

    override suspend fun createBlog(
        authorization: String,
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?
    ): BlogCreateUpdateResponse {
        return openApiAuthService.createBlog(authorization, title, body, image)
    }

    override suspend fun getBlog(authorization: String, slug: String): BlogPost {
        return openApiAuthService.getBlog(authorization, slug).toBlogPost()
    }
}