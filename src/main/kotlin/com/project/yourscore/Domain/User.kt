package com.project.yourscore.Domain

import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open val userId: Long = 0

    lateinit var username: String
    lateinit var password: String
    lateinit var email: String
    var activationCode: String? = null
    var isActive: Boolean = false
}