package com.iaratech2025.remake2026.application.usecase.user.createuser

import com.iaratech2025.remake2026.domain.model.Email
import com.iaratech2025.remake2026.domain.model.User
import java.time.LocalDate
import java.util.UUID

data class CreateUserCommand(
    val name: String,
    val birthday: LocalDate,
    val email: Email,
    val password: String,
    val role: String,
    val manager: User?,
    val gender: User.Gender?,
    val factoryId: UUID
)