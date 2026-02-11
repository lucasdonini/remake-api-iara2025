package com.interdisciplinar2025.remake.domain.exception

sealed class NotFoundException(override val message: String?) : Exception(message) {
    class UserNotFoundByEmail(email: String) : NotFoundException("User with email $email not found")
}