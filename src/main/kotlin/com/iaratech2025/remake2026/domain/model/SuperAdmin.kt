package com.iaratech2025.remake2026.domain.model

private const val INVALID_NAME_MESSAGE = "SuperAdmin's name cannot be blank"
private const val INVALID_JOB_MESSAGE = "SuperAdmin's job cannot be blank"
private const val INVALID_PASSWORD_MESSAGE = "A SuperAdmin must have a password"

class SuperAdmin private constructor(
    name: String,
    job: String,
    var email: Email,
    passwordHash: String
) : AggregateRoot() {
    init {
        require(name.isNotBlank()) { INVALID_NAME_MESSAGE }
        require(job.isNotBlank()) { INVALID_JOB_MESSAGE }
        require(passwordHash.isNotBlank()) { INVALID_PASSWORD_MESSAGE }
    }

    var name: String = name
        set(value) {
            require(value.isNotBlank()) { INVALID_NAME_MESSAGE }
            field = value
        }

    var job: String = job
        set(value) {
            require(value.isNotBlank()) { INVALID_JOB_MESSAGE }
            field = value
        }

    var passwordHash: String = passwordHash
        set(value) {
            require(value.isNotBlank()) { INVALID_PASSWORD_MESSAGE }
            field = value
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

    enum class SuperAdmSortableField : SortableField<SuperAdmin> { NAME, JOB, EMAIL }
}