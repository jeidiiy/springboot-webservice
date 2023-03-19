package io.jeidiiy.springbootwebservice.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class PostsRepositoryTest @Autowired constructor(
    private val postsRepository: PostsRepository
) {
    @AfterEach
    fun cleanup() = postsRepository.deleteAll()

    @Test
    fun BaseTimeEntity_등록() {
        //given
        val now = LocalDateTime.of(2019, 6, 4, 0, 0, 0)
        postsRepository.save(
            Posts(
                title = "title",
                content = "content",
                author = "author"
            )
        )

        //when
        val postsList = postsRepository.findAll()

        //then
        val posts = postsList[0]

        println(">>>>>>>>>> createDate=${posts.createdDate}+modifiedDate=${posts.modifiedDate}")

        assertThat(posts.createdDate).isAfter(now)
        assertThat(posts.modifiedDate).isAfter(now)
    }

    @Test
    fun 게시글저장_불러오기() {
        //given
        val title = "테스트 게시글"
        val content = "테스트 본문"

        postsRepository.save(Posts(title = title, content = content, author = "jeidiiy@gmail.com"))

        //when
        val postsList = postsRepository.findAll()

        //then
        val posts = postsList[0]
        assertThat(posts.title).isEqualTo(title)
        assertThat(posts.content).isEqualTo(content)
    }
}
