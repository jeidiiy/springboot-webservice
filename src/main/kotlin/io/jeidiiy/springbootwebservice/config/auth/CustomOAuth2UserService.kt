package io.jeidiiy.springbootwebservice.config.auth

import io.jeidiiy.springbootwebservice.config.auth.dto.OAuthAttributes
import io.jeidiiy.springbootwebservice.config.auth.dto.SessionUser
import io.jeidiiy.springbootwebservice.domain.user.User
import io.jeidiiy.springbootwebservice.domain.user.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpSession

@Service
class CustomOAuth2UserService(
    val userRepository: UserRepository,
    val httpSession: HttpSession
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val delegate = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)
        val registrationId = userRequest?.clientRegistration?.registrationId ?: throw IllegalStateException()
        val userNameAttributeName =
            userRequest.clientRegistration?.providerDetails?.userInfoEndpoint?.userNameAttributeName
                ?: throw IllegalStateException()
        val attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.attributes)

        val user = saveOrUpdate(attributes)

        httpSession.setAttribute("user", SessionUser(user))

        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority(user.role.key)),
            attributes.attributes,
            attributes.nameAttributeKey
        )
    }

    private fun saveOrUpdate(attributes: OAuthAttributes): User {
        val user = userRepository.findByEmail(attributes.email)?.update(attributes.name, attributes.picture)
            ?: attributes.toEntity()
        return userRepository.save(user)
    }
}
