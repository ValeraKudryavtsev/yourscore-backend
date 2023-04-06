package com.project.yourscore.Controllers

import com.project.yourscore.Domain.User
import com.project.yourscore.LoginData
import com.project.yourscore.Services.UserService
import com.project.yourscore.UpdateUserData
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @PostMapping("/registration")
    fun registration(@RequestBody user: User): Boolean {
        return userService.addUser(user)
    }

    @GetMapping("/login")
    fun login(@RequestBody user: LoginData): User? {
        return userService.loginUser(user)
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody user: UpdateUserData): Boolean {
        return userService.updateUser(user)
    }

    @PutMapping("/{code}")
    fun activateUser(@PathVariable code: String): Boolean {
        return userService.activateUser(code)
    }

    @DeleteMapping("/remove/{id}")
    fun removeUser(@PathVariable id: Long): Boolean {
        return userService.removeUser(id)
    }

}