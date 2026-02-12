package com.iaratech2025.remake2026.application.usecase.createuser

import com.iaratech2025.remake2026.application.port.PasswordHasher
import com.iaratech2025.remake2026.domain.model.User
import com.iaratech2025.remake2026.domain.repository.UserRepository
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime

@Component
class CreateUserHandler(val repository: UserRepository, val hasher: PasswordHasher) {
    fun handle(command: CreateUserCommand): User {
        val hashedPassword = hasher.hash(command.password)
        val permissions = mutableListOf(User.Permission.READ)
        val clock = Clock.systemUTC()

        val user = User.create(
            manager = command.manager,
            name = command.name,
            birthday = command.birthday,
            email = command.email,
            passwordHash = hashedPassword,
            role = command.role,
            gender = command.gender,
            employerFactoryId = command.factoryId,
            isActive = true,
            permissions = permissions,
            clock = clock,
            createdAt = LocalDateTime.now(clock)
        )

        return repository.save(user)
    }

}