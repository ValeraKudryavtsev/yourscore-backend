package com.project.yourscore.Controllers

import com.project.yourscore.Services.FootballApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FootballApiController(private val footballApiService: FootballApiService) {
    @GetMapping("/matches")
    fun getTodayMatches(): String? {
        return footballApiService.getTodayMatches()
    }

    @GetMapping("/scorers/{code}")
    fun getScorersByCode(@PathVariable("code") code: String): String? {
        return footballApiService.getScorersByCode(code)
    }

    @GetMapping("/standings/{code}")
    fun getStandingsByCode(@PathVariable("code") code: String): String? {
        return footballApiService.getStandingsByCode(code)
    }
}