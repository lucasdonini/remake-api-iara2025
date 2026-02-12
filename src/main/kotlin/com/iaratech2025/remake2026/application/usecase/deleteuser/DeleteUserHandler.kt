package com.iaratech2025.remake2026.application.usecase.deleteuser

import com.iaratech2025.remake2026.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class DeleteUserHandler(val repository: UserRepository) {
    fun handle(command: DeleteUserCommand) = repository.deleteById(command.id)
}