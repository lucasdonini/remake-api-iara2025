package com.iaratech2025.remake2026.common.builders

import com.iaratech2025.remake2026.domain.model.Email
import com.iaratech2025.remake2026.domain.model.SuperAdmin

class SuperAdminBuilder {
    private var name: String = "Lucas Kluska Donini"
    private var job: String = "Backend Developer, System Designer, et al"
    private var email: Email = Email.create("lucas.donini@email.com") // Not my real email
    private var passwordHash: String = "my password"

    fun withName(name: String) = apply { this.name = name }
    fun withJob(job: String) = apply { this.job = job }
    fun withEmail(email: Email) = apply { this.email = email }
    fun withPasswordHash(passwordHash: String) = apply { this.passwordHash = passwordHash }

    fun build(): SuperAdmin = SuperAdmin.create(
        name = name,
        job = job,
        email = email,
        passwordHash = passwordHash
    )
}