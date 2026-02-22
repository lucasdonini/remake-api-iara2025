package com.iaratech2025.remake2026.application.exception

sealed class ApplicationConflictException(message: String) : Exception(message) {
    class EmailAlreadyExists : ApplicationConflictException("Email already exists")
}