package io.jeidiiy.springbootwebservice.config.auth

import io.jeidiiy.springbootwebservice.domain.user.Role
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig(
    val customOAuth2UserService: CustomOAuth2UserService
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
            .antMatchers("/api/v1/**").hasRole(Role.USER.name)
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService)

        return http.build()
    }
}
