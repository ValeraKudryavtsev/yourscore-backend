package com.project.yourscore.Controllers

import com.project.yourscore.DataClasses.JwtResponse
import com.project.yourscore.DataClasses.LoginRequest
import com.project.yourscore.DataClasses.RegistrationData
import com.project.yourscore.DataClasses.UpdateUserData
import com.project.yourscore.Domain.User
import com.project.yourscore.Services.AuthService
import com.project.yourscore.Services.UserService
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun auth(@RequestBody loginRequest: LoginRequest): JwtResponse? {
        return authService.getJwtDtoByAuthRequest(loginRequest)
    }

    @PostMapping("/registration")
    fun registration(@RequestBody registrationData: RegistrationData): Boolean {
        return userService.addUser(registrationData)
    }

    @GetMapping("/check/{username}")
    fun checkUsername(@PathVariable username: String): User? {
        return userService.checkUsername(username)
    }

    @GetMapping("/get")
    fun getUserInfo(request: HttpServletRequest): User? {
        val username = authService.getUsernameByRequest(request)
        return userService.getUserInfo(username)
    }

    @PutMapping("/update-username/{new}")
    fun updateUsername(request: HttpServletRequest, @PathVariable new: String): Boolean {
        val username = authService.getUsernameByRequest(request)
        return userService.updateUsername(username, new)
    }

    @PutMapping("/update-email/{email}")
    fun updateEmail(request: HttpServletRequest, @PathVariable email: String): Boolean {
        val username = authService.getUsernameByRequest(request)
        return userService.updateEmail(username, email)
    }

    @PutMapping("/update-password/{password}")
    fun updatePassword(request: HttpServletRequest, @PathVariable password: String): Boolean {
        val username = authService.getUsernameByRequest(request)
        return userService.updatePassword(username, password)
    }

    @PutMapping("/activate/{code}")
    fun activateUser(@PathVariable code: String): Boolean {
        return userService.activateUser(code)
    }

    @DeleteMapping("/remove")
    fun removeUser(request: HttpServletRequest): Boolean {
        val username = authService.getUsernameByRequest(request)
        return userService.removeUser(username)
    }

}