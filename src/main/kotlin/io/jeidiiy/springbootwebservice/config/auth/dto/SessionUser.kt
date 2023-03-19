package io.jeidiiy.springbootwebservice.config.auth.dto

import io.jeidiiy.springbootwebservice.domain.user.User
import java.io.Serializable

class SessionUser(user: User?) : Serializable {
    val name: String?
    val email: String?
    val picture: String?

    init {
        name = user?.name
        email = user?.email
        picture = user?.picture
    }
}
