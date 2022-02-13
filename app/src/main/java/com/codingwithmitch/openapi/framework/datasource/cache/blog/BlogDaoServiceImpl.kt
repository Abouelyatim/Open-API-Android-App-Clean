package com.codingwithmitch.openapi.framework.datasource.cache.blog

import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.framework.datasource.cache.blog.database.BlogPostDao
import com.codingwithmitch.openapi.framework.datasource.cache.blog.database.returnOrderedBlogQuery
import com.codingwithmitch.openapi.framework.datasource.cache.blog.model.toBlogPost
import com.codingwithmitch.openapi.framework.datasource.cache.blog.model.toEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogDaoServiceImpl
@Inject
constructor(
    private val blogPostDao: BlogPostDao
): BlogDaoService {
    override suspend fun insert(blogPost: BlogPost): Long {
        return blogPostDao.insert(blogPost.toEntity())
    }

    override suspend fun deleteBlogPost(blogPost: BlogPost) {
        return blogPostDao.deleteBlogPost(blogPost.toEntity())
    }

    override suspend fun deleteBlogPost(pk: Int) {
        return blogPostDao.deleteBlogPost(pk)
    }

    override suspend fun updateBlogPost(pk: Int, title: String, body: String, image: String) {
        return blogPostDao.updateBlogPost(pk,title,body, image)
    }

    override suspend fun getAllBlogPosts(query: String, page: Int, pageSize: Int): List<BlogPost> {
        return blogPostDao.getAllBlogPosts(query, page, pageSize).map { it -> it.toBlogPost() }
    }

    override suspend fun searchBlogPostsOrderByDateDESC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<BlogPost> {
        return blogPostDao.searchBlogPostsOrderByDateDESC(
            query,
            page,
            pageSize
        ).map { it -> it.toBlogPost() }
    }

    override suspend fun searchBlogPostsOrderByDateASC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<BlogPost> {
        return blogPostDao.searchBlogPostsOrderByDateASC(
            query,
            page,
            pageSize
        ).map { it -> it.toBlogPost() }
    }

    override suspend fun searchBlogPostsOrderByAuthorDESC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<BlogPost> {
        return blogPostDao.searchBlogPostsOrderByAuthorDESC(
            query,
            page,
            pageSize
        ).map { it -> it.toBlogPost() }
    }

    override suspend fun searchBlogPostsOrderByAuthorASC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<BlogPost> {
        return blogPostDao.searchBlogPostsOrderByAuthorASC(
            query,
            page,
            pageSize
        ).map { it -> it.toBlogPost() }
    }

    override suspend fun getBlogPost(pk: Int): BlogPost? {
        return blogPostDao.getBlogPost(pk)?.toBlogPost()
    }

    override suspend fun returnOrderedBlogQuery(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<BlogPost> {
        return blogPostDao.returnOrderedBlogQuery(
            query, filterAndOrder, page
        ).map { it -> it.toBlogPost() }
    }
}