package com.project.yourscore.Configs

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class WebSecurityConfiguration {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(8)
    }

    @Bean
    fun securityWebFilterChain(httpSecurity: ServerHttpSecurity): SecurityWebFilterChain {
        return httpSecurity
            .formLogin().and()
            .httpBasic().disable()
            .authorizeExchange()
                // ***
            .pathMatchers("/", "/login", "/signup").permitAll()
                // ***
            .pathMatchers(HttpMethod.POST, "/api/users").permitAll()
            .anyExchange().authenticated()
            .and()
            .build()
    }
}