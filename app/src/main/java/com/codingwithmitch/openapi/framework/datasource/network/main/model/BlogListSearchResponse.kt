package com.codingwithmitch.openapi.framework.datasource.network.main.model

import com.codingwithmitch.openapi.business.domain.models.BlogPost
import com.google.gson.annotations.SerializedName

class BlogListSearchResponse(

    @SerializedName("results")
    var results: List<BlogPostDto>,

    @SerializedName("detail")
    var detail: String
)

fun BlogListSearchResponse.toList(): List<BlogPost>{
    val list: MutableList<BlogPost> = mutableListOf()
    for(dto in results){
        list.add(
            dto.toBlogPost()
        )
    }
    return list
}






