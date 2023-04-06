package com.project.yourscore.Services

import com.project.yourscore.Domain.User
import com.project.yourscore.LoginData
import com.project.yourscore.Repos.UserRepositoryInterface
import com.project.yourscore.UpdateUserData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (
    @Autowired
    private val userRepositoryInterface: UserRepositoryInterface,
    @Autowired
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails? {
        return userRepositoryInterface.findUserByUsername(username)
    }

    fun addUser(user: User): Boolean {

        if(loadUserByUsername(user.username) != null) {
            return false
        }

        user.isActive = false
        user.activationCode = UUID.randomUUID().toString()
        user.password = passwordEncoder.encode(user.password)

        userRepositoryInterface.save(user)

        return true
    }

    fun removeUser(id: Long): Boolean {
        if(userRepositoryInterface.findById(id) == null)
            return false

        userRepositoryInterface.deleteById(id)

        return true
    }

    fun loginUser(user: LoginData): User? {
        val userFromDb = userRepositoryInterface.findUserByUsername(user.username)

        if(userFromDb.password == passwordEncoder.encode(user.password) || userFromDb.isActive == false)
            return null

        return userFromDb
    }

    fun updateUser(updateUserData: UpdateUserData): Boolean {
        val userFromDb = userRepositoryInterface.findUserByUsername(updateUserData.username) ?: return false

        userFromDb.username = updateUserData.username
        userFromDb.password = passwordEncoder.encode(updateUserData.password)
        userFromDb.email = updateUserData.email

        return true
    }

    fun activateUser(code: String): Boolean {
        val userFromDb = userRepositoryInterface.findByActivationCode(code) ?: return false

        userFromDb.isActive = true
        userFromDb.activationCode = null

        userRepositoryInterface.save(userFromDb)

        return true
    }

}