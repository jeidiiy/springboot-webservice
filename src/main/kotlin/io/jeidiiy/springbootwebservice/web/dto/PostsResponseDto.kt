package io.jeidiiy.springbootwebservice.web.dto

import io.jeidiiy.springbootwebservice.domain.posts.Posts

class PostsResponseDto(entity: Posts) {
    val title: String
    val content: String
    val author: String
    val id: Long?

    init {
        this.id = entity.id
        this.title = entity.title
        this.content = entity.content
        this.author = entity.author
    }
}
