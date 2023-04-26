package com.project.yourscore.Controllers

import com.project.yourscore.DataClasses.JwtResponse
import com.project.yourscore.DataClasses.LoginRequest
import com.project.yourscore.DataClasses.RegistrationData
import com.project.yourscore.DataClasses.UpdateUserData
import com.project.yourscore.Domain.User
import com.project.yourscore.Services.AuthService
import com.project.yourscore.Services.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


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
    fun getUserInfo() {

    }

    @PutMapping("/update")
    fun updateUser(@RequestBody user: UpdateUserData): Boolean {
        return userService.updateUser(user)
    }

    @PutMapping("/activate/{code}")
    fun activateUser(@PathVariable code: String): Boolean {
        return userService.activateUser(code)
    }

    @DeleteMapping("/remove/{username}")
    fun removeUser(@PathVariable username: String): Boolean {
        return userService.removeUser(username)
    }

}