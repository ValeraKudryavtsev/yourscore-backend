package com.project.yourscore.Configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class WebSecurityConfiguration(
    @Autowired
    private var jwtFilter: JwtFilter
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(8)
    }

    @Bean
    fun doSecurityFilter(http: HttpSecurity): SecurityFilterChain? {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/error").permitAll()
            .antMatchers("/user/registration", "/user/check/*", "/user/activate/*", "/user/login",
                "/standings/*", "/matches", "/scorers/*").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().disable()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

}