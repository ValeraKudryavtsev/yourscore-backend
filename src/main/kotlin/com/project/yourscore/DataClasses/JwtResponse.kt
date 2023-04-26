package com.project.yourscore.DataClasses

import java.time.OffsetDateTime

data class JwtResponse(
    var token: String,
    var issuedAt: OffsetDateTime,
    var expiration: OffsetDateTime
)
