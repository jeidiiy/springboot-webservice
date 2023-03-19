package io.jeidiiy.springbootwebservice.web.dto

import io.jeidiiy.springbootwebservice.domain.posts.Posts
import java.time.LocalDateTime

class PostsListResponseDto(entity: Posts) {

    val title: String
    val author: String
    val modifiedDate: LocalDateTime
    val id: Long?

    init {
        this.id = entity.id
        this.title = entity.title
        this.author = entity.author
        this.modifiedDate = entity.modifiedDate
    }
}
