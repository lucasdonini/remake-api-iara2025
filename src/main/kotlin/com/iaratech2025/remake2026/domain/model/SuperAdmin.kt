package com.iaratech2025.remake2026.domain.model

class SuperAdmin private constructor(
    val name: String,
    val job: String,
    val email: Email,
    val passwordHash: String
) : AggregateRoot() {
    init {
        require(name.isNotBlank()) { "SuperAdmin's name cannot be blank" }
        require(job.isNotBlank()) { "SuperAdmin's job cannot be blank" }
        require(passwordHash.isNotBlank()) { "A SuperAdmin must have a password" }
    }

    companion object {
        fun create(
            name: String,
            job: String,
            email: Email,
            passwordHash: String
        ): SuperAdmin = SuperAdmin(
            name = name,
            job = job,
            email = email,
            passwordHash = passwordHash
        )
    }
}