package io.jeidiiy.springbootwebservice.web.dto

import io.jeidiiy.springbootwebservice.domain.posts.Posts

class PostsSaveRequestDto(
    val title: String,
    val content: String,
    val author: String
) {
    fun toEntity() = Posts(title = title, content = content, author = author)
}
