package com.project.yourscore.Configs

import com.project.yourscore.Services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {
    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Bean
    fun getPasswordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder(8)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userService)
            .passwordEncoder(passwordEncoder)
    }
}