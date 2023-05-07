package com.project.yourscore.Services

import com.project.yourscore.DataClasses.TeamData
import com.project.yourscore.Domain.Team
import com.project.yourscore.Repos.TeamRepositoryInterface
import com.project.yourscore.Repos.UserRepositoryInterface
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class TeamService (
    private val teamRepositoryInterface: TeamRepositoryInterface,
    private val userRepositoryInterface: UserRepositoryInterface,
    private val authService: AuthService
) {
    fun addTeamToFollowed(teamData: TeamData, request: HttpServletRequest) {
        val token: String = request.getHeader(HttpHeaders.AUTHORIZATION)
        val username = authService.verifyTokenAndGetJws(token).body.get("username", String::class.java)
        val userFromDb = userRepositoryInterface.findByUsername(username)

        val team = Team()
        team.teamCode = teamData.teamCode
        team.teamEmblemUrl = teamData.teamEmblemUrl
        team.teamName = teamData.teamName
        team.user = userFromDb!!

        teamRepositoryInterface.save(team)
    }

    fun deleteTeamFromFollowed(teamId: String) {
        teamRepositoryInterface.deleteById(teamId.toLong())
    }

    fun getFollowedTeams(request: HttpServletRequest): List<Team> {
        val token: String = request.getHeader(HttpHeaders.AUTHORIZATION)
        val username = authService.verifyTokenAndGetJws(token).body.get("username", String::class.java)
        val userFromDb = userRepositoryInterface.findByUsername(username)

        return teamRepositoryInterface.findAllByUser(userFromDb!!)
    }
}