package com.iaratech2025.remake2026.application.exception

import java.util.UUID

sealed class ApplicationNotFoundException(message: String) : Exception(message) {
    class SuperAdmNotFoundByIdException(id: UUID) : ApplicationNotFoundException("Super admin with email $id not found")
    class UserNotFoundByEmailException(email: String) : ApplicationNotFoundException("User with email $email not found")
    class UserNotFoundByIdException(id: String) : ApplicationNotFoundException("User with id $id not found")
}