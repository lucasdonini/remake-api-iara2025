package com.iaratech2025.remake2026.application.usecase.superadm.update

import com.iaratech2025.remake2026.application.exception.NotFoundException.SuperAdmNotFoundByIdException
import com.iaratech2025.remake2026.application.exception.UnauthorizedException.IncorrectPasswordException
import com.iaratech2025.remake2026.application.exception.BadRequestException.MissingCurrentPasswordException
import com.iaratech2025.remake2026.application.port.PasswordHasher
import com.iaratech2025.remake2026.domain.model.SuperAdmin
import com.iaratech2025.remake2026.domain.repository.SuperAdmRepository
import org.springframework.stereotype.Component

@Component
class UpdateSuperAdmHandler(val repository: SuperAdmRepository, val hasher: PasswordHasher) {
    fun handle(command: UpdateSuperAdmCommand): SuperAdmin {

        val superAdm = repository.findById(command.id) ?: throw SuperAdmNotFoundByIdException(command.id)

        command.name?.let { superAdm.name = it }
        command.email?.let { superAdm.email = it }
        command.job?.let { superAdm.job = it }

        if (command.newPassword != null) {
            if (command.currentPassword == null) throw MissingCurrentPasswordException()
            if (!hasher.compare(password = command.currentPassword, hashed = superAdm.passwordHash)) throw IncorrectPasswordException()
            superAdm.passwordHash = hasher.hash(command.newPassword)
        }

        return superAdm
    }
}