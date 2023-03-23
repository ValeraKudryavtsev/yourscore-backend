package com.project.yourscore.Controllers

import com.project.yourscore.Services.GetStandingsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/standings")
class GetStandingsController(private val getStandingsService: GetStandingsService) {

    @GetMapping("/{code}")
    fun getStandingsByCode(@PathVariable("code") code: String): String? {
        return getStandingsService.getStandingsByCode(code)
    }
}