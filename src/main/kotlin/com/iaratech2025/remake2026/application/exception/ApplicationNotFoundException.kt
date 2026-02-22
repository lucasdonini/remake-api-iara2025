package com.iaratech2025.remake2026.application.exception

import java.util.UUID

sealed class ApplicationNotFoundException(message: String) : Exception(message) {
    class SuperAdmNotFoundByIdException(id: UUID) : ApplicationNotFoundException("Super admin with email $id not found")
}