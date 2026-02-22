package com.iaratech2025.remake2026.application.usecase.superadm.create

import com.iaratech2025.remake2026.application.exception.ApplicationConflictException
import com.iaratech2025.remake2026.application.port.PasswordHasher
import com.iaratech2025.remake2026.domain.model.SuperAdmin
import com.iaratech2025.remake2026.domain.repository.SuperAdmRepository
import org.springframework.stereotype.Component

@Component
class CreateSuperAdmHandler(val repository: SuperAdmRepository, val hasher: PasswordHasher) {
    fun handle(command: CreateSuperAdmCommand): SuperAdmin {
        if (repository.existsByEmail(command.email.value))
            throw ApplicationConflictException.EmailAlreadyExists()

        val superAdm = SuperAdmin.create(
            name = command.name,
            job = command.job,
            email = command.email,
            passwordHash = hasher.hash(command.password)
        )

        return repository.save(superAdm)
    }
}