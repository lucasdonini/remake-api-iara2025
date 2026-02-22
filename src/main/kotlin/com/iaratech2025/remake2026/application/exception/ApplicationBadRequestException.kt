package com.iaratech2025.remake2026.application.exception

sealed class ApplicationBadRequestException(message: String) : Exception(message) {
    class MissingCurrentPasswordException : ApplicationBadRequestException("Current password is required to set a new password")
}