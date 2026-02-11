package com.iaratech2025.remake2026.application.usecase.login

import com.iaratech2025.remake2026.domain.model.Email

data class LoginCommand(
    val email: Email,
    val password: String
)
