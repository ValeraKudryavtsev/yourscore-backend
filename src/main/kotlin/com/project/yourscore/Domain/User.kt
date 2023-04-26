package com.project.yourscore.Domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var userId: Long = 0
    private var username: String = ""
    private var password: String = ""
    var email: String = ""
    var activationCode: String? = null
    var isActive: Boolean = false
    var role: Role? = null

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf(SimpleGrantedAuthority(role?.name))
    }

    override fun getPassword(): String {
        return this.password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getUsername(): String {
        return this.username
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
        return true
    }
}