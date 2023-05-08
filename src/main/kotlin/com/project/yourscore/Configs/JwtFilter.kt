package com.project.yourscore.Configs

import com.project.yourscore.Services.AuthService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Slf4j
class JwtFilter(private var authService: AuthService) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (token != null && "" != token) {
            try {
                authService.authorizeUserByToken(token)
            } catch (e: Exception) {
                val message = e.message
                println(message)
            }
        }
        filterChain.doFilter(request, response)
    }
}