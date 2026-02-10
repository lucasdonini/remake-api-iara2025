package com.interdisciplinar2025.remake.common.builders

import com.interdisciplinar2025.remake.domain.model.Email
import com.interdisciplinar2025.remake.domain.model.SuperAdmin

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