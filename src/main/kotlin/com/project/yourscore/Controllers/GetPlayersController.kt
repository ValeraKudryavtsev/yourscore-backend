package com.project.yourscore.Controllers

import com.project.yourscore.Services.GetPlayersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/scorers")
class GetPlayersController(private val getPlayersService: GetPlayersService) {

    @GetMapping("/{code}")
    fun getScorersByCode(@PathVariable("code") code: String): String? {
        return getPlayersService.getScorersByCode(code)
    }
}