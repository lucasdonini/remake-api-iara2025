package com.iaratech2025.remake2026.domain.exception

sealed class NotFoundException(override val message: String?) : Exception(message) {
    class UserNotFoundByEmailException(email: String) : NotFoundException("User with email $email not found")
    class UserNotFoundByIdException(id: String) : NotFoundException("User with id $id not found")
}