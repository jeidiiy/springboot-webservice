package io.jeidiiy.springbootwebservice.config.auth.dto

import io.jeidiiy.springbootwebservice.domain.user.Role
import io.jeidiiy.springbootwebservice.domain.user.User

class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String
) {
    companion object {
        fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>) =
            ofGoogle(userNameAttributeName, attributes)

        private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>) =
            OAuthAttributes(
                name = attributes["name"].toString(),
                email = attributes["email"].toString(),
                picture = attributes["picture"].toString(),
                attributes = attributes,
                nameAttributeKey = userNameAttributeName
            )
    }

    fun toEntity(): User = User(name = name, email = email, picture = picture, role = Role.GUEST)
}
