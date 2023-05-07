package com.project.yourscore.Repos

import com.project.yourscore.Domain.Team
import com.project.yourscore.Domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepositoryInterface : JpaRepository<Team, Long> {
    fun findAllByUser(user: User): List<Team>
}