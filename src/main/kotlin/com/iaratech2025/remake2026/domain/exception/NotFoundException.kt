package com.iaratech2025.remake2026.domain.exception

sealed class NotFoundException(override val message: String?) : Exception(message) {
    class UserNotFoundByEmail(email: String) : NotFoundException("User with email $email not found")
}