package com.project.yourscore.Controllers

import com.project.yourscore.Services.GetMatchesService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/matches")
class GetMatchesController(private val getMatchesService: GetMatchesService) {

    @GetMapping()
    fun getTodayMatches(): String? {
        return getMatchesService.getTodayMatches()
    }

}