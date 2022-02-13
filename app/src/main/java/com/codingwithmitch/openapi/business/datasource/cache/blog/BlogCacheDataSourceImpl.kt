package com.codingwithmitch.openapi.business.datasource.cache.blog

import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.codingwithmitch.openapi.framework.datasource.cache.blog.BlogDaoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogCacheDataSourceImpl
@Inject
constructor(
    private val blogDaoService: BlogDaoService
): BlogCacheDataSource {
    override suspend fun insert(blogPost: BlogPost): Long {
        return blogDaoService.insert(blogPost)
    }

    override suspend fun deleteBlogPost(blogPost: BlogPost) {
        return blogDaoService.deleteBlogPost(blogPost)
    }

    override suspend fun deleteBlogPost(pk: Int) {
        return blogDaoService.deleteBlogPost(pk)
    }

    override suspend fun updateBlogPost(pk: Int, title: String, body: String, image: String) {
        return blogDaoService.updateBlogPost(pk,title,body,image)
    }

    override suspend fun getAllBlogPosts(query: String, page: Int, pageSize: Int): List<BlogPost> {
        return blogDaoService.getAllBlogPosts(query,page,pageSize)
    }

    override suspend fun searchBlogPostsOrderByDateDESC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<BlogPost> {
        return blogDaoService.searchBlogPostsOrderByDateDESC(
            query,
            page,
            pageSize
        )
    }

    override suspend fun searchBlogPostsOrderByDateASC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<BlogPost> {
        return blogDaoService.searchBlogPostsOrderByDateASC(
            query,
            page,
            pageSize
        )
    }

    override suspend fun searchBlogPostsOrderByAuthorDESC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<BlogPost> {
        return blogDaoService.searchBlogPostsOrderByAuthorDESC(
            query,
            page,
            pageSize
        )
    }

    override suspend fun searchBlogPostsOrderByAuthorASC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<BlogPost> {
        return blogDaoService.searchBlogPostsOrderByAuthorASC(
            query,
            page,
            pageSize
        )
    }

    override suspend fun getBlogPost(pk: Int): BlogPost? {
        return blogDaoService.getBlogPost(pk)
    }

    override suspend fun returnOrderedBlogQuery(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<BlogPost> {
        return blogDaoService.returnOrderedBlogQuery(
            query,
            filterAndOrder,
            page
        )
    }
}