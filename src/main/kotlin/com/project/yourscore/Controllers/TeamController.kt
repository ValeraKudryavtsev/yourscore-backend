package com.project.yourscore.Controllers

import com.project.yourscore.DataClasses.TeamData
import com.project.yourscore.Domain.Team
import com.project.yourscore.Services.AuthService
import com.project.yourscore.Services.TeamService
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/team")
class TeamController(
    private val teamService: TeamService
) {
    @PostMapping("/follow")
    fun addTeamToFollowed(@RequestBody teamData: TeamData, request: HttpServletRequest) {
        teamService.addTeamToFollowed(teamData, request)
    }

    @DeleteMapping("/delete/{teamId}")
    fun deleteTeamFromFollowed(@PathVariable teamId: String) {
        teamService.deleteTeamFromFollowed(teamId)
    }

    @GetMapping("/list")
    fun getFollowedTeams(request: HttpServletRequest): List<Team> {
        return teamService.getFollowedTeams(request)
    }
}