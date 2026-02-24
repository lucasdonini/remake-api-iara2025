package com.iaratech2025.remake2026.application.exception

sealed class BadRequestException(message: String) : Exception(message) {
    class MissingCurrentPasswordException : BadRequestException("Current password is required to set a new password")
}