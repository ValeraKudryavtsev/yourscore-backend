package com.project.yourscore.Repos

import com.project.yourscore.Domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepositoryInterface : JpaRepository<User, Long> {

    fun findByUsername(username: String) : User?

    fun findByActivationCode(activationCode: String) : User?
}