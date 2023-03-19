package io.jeidiiy.springbootwebservice.domain.posts

import javax.persistence.*

@Entity
class Posts(
    @Column(length = 500, nullable = false) var title: String,
    @Column(columnDefinition = "TEXT", nullable = false) var content: String,
    val author: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
) {
    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
