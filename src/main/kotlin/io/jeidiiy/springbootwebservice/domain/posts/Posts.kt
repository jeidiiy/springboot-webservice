package io.jeidiiy.springbootwebservice.domain.posts

import javax.persistence.*

@Entity
class Posts(
    @Column(length = 500, nullable = false) val title: String,
    @Column(columnDefinition = "TEXT", nullable = false) val content: String,
    val author: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
)
