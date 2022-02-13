package com.codingwithmitch.openapi.framework.datasource.cache.blog

import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.business.domain.util.Constants

interface BlogDaoService {

    suspend fun insert(blogPost: BlogPost): Long

    suspend fun deleteBlogPost(blogPost: BlogPost)

    suspend fun deleteBlogPost(pk: Int)

    suspend fun updateBlogPost(pk: Int, title: String, body: String, image: String)

    suspend fun getAllBlogPosts(
        query: String,
        page: Int,
        pageSize: Int = Constants.PAGINATION_PAGE_SIZE
    ): List<BlogPost>

    suspend fun searchBlogPostsOrderByDateDESC(
        query: String,
        page: Int,
        pageSize: Int = Constants.PAGINATION_PAGE_SIZE
    ): List<BlogPost>

    suspend fun searchBlogPostsOrderByDateASC(
        query: String,
        page: Int,
        pageSize: Int = Constants.PAGINATION_PAGE_SIZE
    ): List<BlogPost>

    suspend fun searchBlogPostsOrderByAuthorDESC(
        query: String,
        page: Int,
        pageSize: Int = Constants.PAGINATION_PAGE_SIZE
    ): List<BlogPost>

    suspend fun searchBlogPostsOrderByAuthorASC(
        query: String,
        page: Int,
        pageSize: Int = Constants.PAGINATION_PAGE_SIZE
    ): List<BlogPost>
    suspend fun getBlogPost(pk: Int): BlogPost?

    suspend fun returnOrderedBlogQuery(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<BlogPost>
}