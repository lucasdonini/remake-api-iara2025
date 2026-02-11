package com.interdisciplinar2025.remake.application.usecase.login

import com.interdisciplinar2025.remake.application.port.PasswordHasher
import com.interdisciplinar2025.remake.domain.exception.NotFoundException
import com.interdisciplinar2025.remake.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class LoginHandler(val repository: UserRepository, val hasher: PasswordHasher) {
    fun handle(command: LoginCommand): Boolean {
        val user = repository.getByEmail(command.email.value)
            ?: throw NotFoundException.UserNotFoundByEmail(command.email.value)

        return hasher.compare(password = command.password, hashed = user.passwordHash)
    }
}