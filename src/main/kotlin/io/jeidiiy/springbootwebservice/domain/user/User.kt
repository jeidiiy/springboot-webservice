package io.jeidiiy.springbootwebservice.domain.user

import io.jeidiiy.springbootwebservice.domain.BaseTimeEntity
import javax.persistence.*

@Table(name = "users")
@Entity
class User(
    @Column(nullable = false) var name: String,
    @Column(nullable = false) var email: String,
    @Column var picture: String,
    @Enumerated(EnumType.STRING) @Column(nullable = false) var role: Role,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
) : BaseTimeEntity() {
    fun update(name: String, picture: String): User {
        this.name = name
        this.picture = picture
        return this
    }
}
