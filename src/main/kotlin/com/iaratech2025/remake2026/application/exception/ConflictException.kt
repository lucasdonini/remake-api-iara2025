package com.iaratech2025.remake2026.application.exception

sealed class ConflictException(message: String) : Exception(message) {
    class EmailAlreadyExists : ConflictException("Email already exists")
}