package com.iaratech2025.remake2026.application.usecase.updateuser

import com.iaratech2025.remake2026.domain.exception.NotFoundException
import com.iaratech2025.remake2026.domain.model.User
import com.iaratech2025.remake2026.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UpdateUserHandler(val repository: UserRepository) {

    fun handle(command: UpdateUserCommand): User {
        val user = repository.findById(command.id) ?: throw NotFoundException.UserNotFoundByIdException(command.id.toString())

        command.name?.let { user.name = it }
        command.isActive?.let { user.isActive = it }
        command.role?.let { user.role = it }
        command.manager?.let { user.manager = it}
        command.gender?.let { user.gender = it }
        command.factoryId?.let { user.employerFactoryId = it }
        command.permissions?.let { user.permissions = it }
        command.email?.let { user.email = it }

        return user
    }
}