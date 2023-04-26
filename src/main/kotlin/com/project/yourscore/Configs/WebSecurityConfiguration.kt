package com.project.yourscore.Configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
//@EnableWebSecurity
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
            .antMatchers(HttpMethod.POST, "/user/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().disable()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

//    override fun configure(http: HttpSecurity) {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/user/registration", "/user/check/*", "/user/activate/*",
//                "/standings/*", "/matches", "/scorers/*").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/user/login")
//            .permitAll()
//            .and()
//            .logout()
//            .logoutSuccessUrl("/user/login?logout")
//            .invalidateHttpSession(true)
//            .permitAll()
//    }
}