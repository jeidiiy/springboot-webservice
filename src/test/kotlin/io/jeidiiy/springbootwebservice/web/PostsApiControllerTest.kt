package io.jeidiiy.springbootwebservice.web

import com.fasterxml.jackson.databind.ObjectMapper
import io.jeidiiy.springbootwebservice.domain.posts.Posts
import io.jeidiiy.springbootwebservice.domain.posts.PostsRepository
import io.jeidiiy.springbootwebservice.web.dto.PostsSaveRequestDto
import io.jeidiiy.springbootwebservice.web.dto.PostsUpdateRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest @Autowired constructor(
    private val postsRepository: PostsRepository,
    private val context: WebApplicationContext
) {
    @LocalServerPort
    private var port: Int? = 0

    private var mvc: MockMvc? = null

    @BeforeEach
    fun setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(springSecurity())
            .build()
    }

    @AfterEach
    fun tearDown() = postsRepository.deleteAll()

    @Test
    @WithMockUser(roles = ["USER"])
    fun Posts_수정된다() {
        //given
        val savedPosts = postsRepository.save(Posts(title = "title", content = "content", author = "author"))
        val updateId = savedPosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"

        val requestDto = PostsUpdateRequestDto(title = expectedTitle, content = expectedContent)

        val url = "http://localhost:${port}/api/v1/posts/${updateId}"

        //when
        mvc!!.perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(requestDto))
        ).andExpect(status().isOk)

        //then
        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun Posts_등록된다() {
        //given
        val title = "title"
        val content = "content"
        val requestDto = PostsSaveRequestDto(title = title, content = content, author = "author")
        val url = "http://localhost:${port}/api/v1/posts"

        //when
        mvc!!.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(requestDto))
        ).andExpect(status().isOk)

        //then
        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
    }
}
