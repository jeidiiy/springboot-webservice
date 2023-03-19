package io.jeidiiy.springbootwebservice.web

import io.jeidiiy.springbootwebservice.domain.posts.Posts
import io.jeidiiy.springbootwebservice.domain.posts.PostsRepository
import io.jeidiiy.springbootwebservice.web.dto.PostsSaveRequestDto
import io.jeidiiy.springbootwebservice.web.dto.PostsUpdateRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest @Autowired constructor(
    private val restTemplate: TestRestTemplate,
    private val postsRepository: PostsRepository
) {
    @LocalServerPort
    private var port: Int? = 0

    @AfterEach
    fun tearDown() = postsRepository.deleteAll()

    @Test
    fun Posts_수정된다() {
        //given
        val savedPosts = postsRepository.save(Posts(title = "title", content = "content", author = "author"))
        val updateId = savedPosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"

        val requestDto = PostsUpdateRequestDto(title = expectedTitle, content = expectedContent)

        val url = "http://localhost:${port}/api/v1/posts/${updateId}"

        val requestEntity = HttpEntity(requestDto)

        //when
        val responseEntity: ResponseEntity<Long> =
            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long::class)

        //then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)
        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }

    @Test
    fun Posts_등록된다() {
        //given
        val title = "title"
        val content = "content"
        val requestDto = PostsSaveRequestDto(title = title, content = content, author = "author")
        val url = "http://localhost:${port}/api/v1/posts"

        println(port)

        //when
        val responseEntity = restTemplate.postForEntity(url, requestDto, Long::class.java)

        //then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
    }
}
