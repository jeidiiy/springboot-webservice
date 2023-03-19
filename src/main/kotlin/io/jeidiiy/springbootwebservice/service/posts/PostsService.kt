package io.jeidiiy.springbootwebservice.service.posts

import io.jeidiiy.springbootwebservice.domain.posts.PostsRepository
import io.jeidiiy.springbootwebservice.web.dto.PostsListResponseDto
import io.jeidiiy.springbootwebservice.web.dto.PostsResponseDto
import io.jeidiiy.springbootwebservice.web.dto.PostsSaveRequestDto
import io.jeidiiy.springbootwebservice.web.dto.PostsUpdateRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsService(
    private val postsRepository: PostsRepository
) {
    @Transactional
    fun save(requestDto: PostsSaveRequestDto) = postsRepository.save(requestDto.toEntity()).id

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long {
        val posts =
            postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id=${id}") }

        with(requestDto) {
            posts.update(title, content)
        }

        return id
    }

    fun findById(id: Long): PostsResponseDto {
        val entity = postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id=${id}") }
        return PostsResponseDto(entity)
    }

    @Transactional(readOnly = true)
    fun findAllDesc() = postsRepository.findAllDesc().asSequence().map { PostsListResponseDto(it) }.toList()
}
