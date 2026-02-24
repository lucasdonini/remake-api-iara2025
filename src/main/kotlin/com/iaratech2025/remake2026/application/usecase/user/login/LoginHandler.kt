package com.iaratech2025.remake2026.application.usecase.user.login

import com.iaratech2025.remake2026.application.exception.ApplicationNotFoundException
import com.iaratech2025.remake2026.application.port.PasswordHasher
import com.iaratech2025.remake2026.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class LoginHandler(val repository: UserRepository, val hasher: PasswordHasher) {
    fun handle(command: LoginCommand): Boolean {
        val user = repository.getByEmail(command.email.value)
            ?: throw ApplicationNotFoundException.UserNotFoundByEmailException(command.email.value)

        return hasher.compare(password = command.password, hashed = user.passwordHash)
    }
}