package io.jeidiiy.springbootwebservice.domain.posts

import io.jeidiiy.springbootwebservice.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class Posts(
    @Column(length = 500, nullable = false) var title: String,
    @Column(columnDefinition = "TEXT", nullable = false) var content: String,
    val author: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
) : BaseTimeEntity() {
    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
