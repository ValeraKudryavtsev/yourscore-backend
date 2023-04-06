package com.project.yourscore.Domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class User: UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open val userId: Long = 0

    private lateinit var username: String
    private lateinit var password: String
    lateinit var email: String
    var activationCode: String? = null
    var isActive: Boolean = false
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getUsername(): String {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return isActive
    }
}