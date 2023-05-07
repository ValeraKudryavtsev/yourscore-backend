package com.project.yourscore.Domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open val teamId: Long = 0

    lateinit var teamCode: String
    lateinit var teamName: String
    lateinit var teamEmblemUrl: String

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    lateinit var user: User
}