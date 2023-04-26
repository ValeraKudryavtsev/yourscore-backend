package com.project.yourscore.Services

import com.project.yourscore.DataClasses.JwtResponse
import com.project.yourscore.DataClasses.LoginRequest
import com.project.yourscore.Domain.User
import com.project.yourscore.Repos.UserRepositoryInterface
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*

@Service
class AuthService(
    @Autowired
    private val userRepositoryInterface: UserRepositoryInterface,
    @Value("\${jwt.secret-key}")
    private val key: String? = null,
    @Value("\${jwt.expiration-time}")
    private val expirationNumber: Long? = null
) {
    private var secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key))

    private fun getTokenForUser(user: UserDetails): String {
        return Jwts.builder()
            .signWith(secretKey)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + expirationNumber!!))
            .claim("username", user.username)
            .compact()
    }

    private fun verifyTokenAndGetJws(token: String): Jws<Claims> {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
    }

    fun authorizeUserByToken(token: String) {
        val username = verifyTokenAndGetJws(token).body.get("username", String::class.java)
        val user: User? = userRepositoryInterface.findByUsername(username)
        if (user == null) {
            val message = "User with username %s in claims of JWT not found"
            throw RuntimeException(String.format(message))
        }
        val passwordAuthenticationToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        SecurityContextHolder.getContext().authentication = passwordAuthenticationToken
    }

    fun getJwtDtoByAuthRequest(loginRequest: LoginRequest): JwtResponse? {
        val user: UserDetails = userRepositoryInterface.findByUsername(loginRequest.username) as UserDetails
        val authPassword: String = loginRequest.password
        return if (BCryptPasswordEncoder(8).matches(authPassword, user.password)) {
            val token = getTokenForUser(user)
            val jws: Jws<Claims> = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
            val zoneId = ZoneId.systemDefault()
            val issuedAt = OffsetDateTime.ofInstant(jws.body.issuedAt.toInstant(), zoneId)
            val expiration = OffsetDateTime.ofInstant(jws.body.expiration.toInstant(), zoneId)
            JwtResponse(token, issuedAt, expiration)
        } else {
            throw RuntimeException("Authentication error!")
        }
    }
}