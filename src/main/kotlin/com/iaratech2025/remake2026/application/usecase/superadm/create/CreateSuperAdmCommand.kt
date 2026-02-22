package com.iaratech2025.remake2026.application.usecase.superadm.create

import com.iaratech2025.remake2026.domain.model.Email

data class CreateSuperAdmCommand(
    val name: String,
    val job: String,
    val email: Email,
    val password: String
)
