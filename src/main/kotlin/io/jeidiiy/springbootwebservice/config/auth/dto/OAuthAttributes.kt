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
            when (registrationId) {
                "naver" -> ofNaver("id", attributes)
                "google" -> ofGoogle(userNameAttributeName, attributes)
                else -> throw IllegalArgumentException("No registration Id$: {registrationId}")
            }

        private fun ofNaver(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            val response = attributes["response"] as Map<String, Any>

            return OAuthAttributes(
                name = response["name"] as String,
                email = response["email"] as String,
                picture = response["profile_image"] as String,
                attributes = response,
                nameAttributeKey = userNameAttributeName
            )
        }

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
