package com.iaratech2025.remake2026.application.exception

import java.util.UUID

sealed class NotFoundException(message: String) : Exception(message) {
    class SuperAdmNotFoundByIdException(id: UUID) : NotFoundException("Super admin with email $id not found")
    class UserNotFoundByEmailException(email: String) : NotFoundException("User with email $email not found")
    class UserNotFoundByIdException(id: String) : NotFoundException("User with id $id not found")
}