package com.project.yourscore.Repos

import com.project.yourscore.Domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepositoryInterface : CrudRepository<User, Long> {

    fun findUserByUsername(username: String) : User

    fun findByActivationCode(activationCode: String) : User
}