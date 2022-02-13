package com.codingwithmitch.openapi.framework.datasource.network.main

import com.codingwithmitch.openapi.business.domain.models.Account
import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.framework.datasource.network.main.model.BlogCreateUpdateResponse
import com.codingwithmitch.openapi.framework.datasource.network.main.model.GenericResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface MainNetworkService {

    suspend fun getAccount(authorization: String): Account

    suspend fun updateAccount(authorization: String,email: String,username: String): GenericResponse

    suspend fun updatePassword(
        authorization: String,
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): GenericResponse

    suspend fun searchListBlogPosts(
        authorization: String,
        query: String,
        ordering: String,
        page: Int
    ):  List<BlogPost>

    suspend fun isAuthorOfBlogPost(authorization: String,slug: String): GenericResponse

    suspend fun deleteBlogPost(authorization: String,slug: String): GenericResponse

    suspend fun updateBlog(
        authorization: String,
        slug: String,
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?
    ): BlogCreateUpdateResponse

    suspend fun createBlog(
        authorization: String,
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?
    ): BlogCreateUpdateResponse

    suspend fun getBlog(authorization: String,slug: String,
    ): BlogPost
}