package com.project.yourscore.Repos

import com.project.yourscore.Domain.Team
import org.springframework.data.repository.CrudRepository

interface TeamRepositoryInterface : CrudRepository<Team, Long> {
}