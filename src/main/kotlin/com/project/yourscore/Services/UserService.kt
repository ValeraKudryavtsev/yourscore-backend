package com.project.yourscore.Services

import com.project.yourscore.DataClasses.JwtResponse
import com.project.yourscore.DataClasses.LoginRequest
import com.project.yourscore.DataClasses.RegistrationData
import com.project.yourscore.DataClasses.UpdateUserData
import com.project.yourscore.Domain.Role
import com.project.yourscore.Domain.User
import com.project.yourscore.Repos.UserRepositoryInterface
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class UserService(
    @Autowired
    private val userRepositoryInterface: UserRepositoryInterface,
    @Autowired
    private val emailService: EmailService,
//    @Autowired
//    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    fun addUser(registrationData: RegistrationData): Boolean {
        val user = User()
        user.username = registrationData.username
        user.email = registrationData.email
        user.isActive = false
        user.activationCode = UUID.randomUUID().toString()
        user.password = BCryptPasswordEncoder(8).encode(registrationData.password)
        user.role = Role.ROLE_USER

        userRepositoryInterface.save(user)

        // fix the link
        emailService.sendEmail(
            user.email,
            "Activation Account",
            "Hi, ${user.username}!\nFollow this link to activate YourScore account: http://localhost:3000/user/${user.activationCode}"
        )

        return true
    }

    fun checkUsername(username: String): User? {
        val userFromDb = userRepositoryInterface.findByUsername(username)
        return userFromDb
    }

    fun getUserInfo(username: String): User? {
        return userRepositoryInterface.findByUsername(username)
    }

    fun removeUser(username: String): Boolean {
        val userFromDb = userRepositoryInterface.findByUsername(username) ?: return false

        userRepositoryInterface.deleteById(userFromDb.userId)

        return true
    }

    fun updateUsername(username: String, newUsername: String): Boolean {
        val userFromDb = userRepositoryInterface.findByUsername(username) ?: return false

        userFromDb.username = newUsername
        userRepositoryInterface.save(userFromDb)

        return true
    }

    fun updateEmail(username: String, email: String): Boolean {
        val userFromDb = userRepositoryInterface.findByUsername(username) ?: return false

        userFromDb.email = email
        userRepositoryInterface.save(userFromDb)

        return true
    }

    fun updatePassword(username: String, password: String): Boolean {
        val userFromDb = userRepositoryInterface.findByUsername(username) ?: return false

        userFromDb.password = BCryptPasswordEncoder(8).encode(password)
        userRepositoryInterface.save(userFromDb)

        return true
    }

    fun activateUser(code: String): Boolean {
        val userFromDb = userRepositoryInterface.findByActivationCode(code) ?: return false

        userFromDb.isActive = true
        userFromDb.activationCode = null

        userRepositoryInterface.save(userFromDb)

        return true
    }

    override fun loadUserByUsername(username: String): UserDetails? {
        return userRepositoryInterface.findByUsername(username)
    }
}
