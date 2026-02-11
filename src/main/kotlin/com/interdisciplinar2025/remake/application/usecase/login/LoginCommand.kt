package com.interdisciplinar2025.remake.application.usecase.login

import com.interdisciplinar2025.remake.domain.model.Email

data class LoginCommand(
    val email: Email,
    val password: String
)
