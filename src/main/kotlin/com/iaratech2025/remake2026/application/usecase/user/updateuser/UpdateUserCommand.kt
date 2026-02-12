package com.iaratech2025.remake2026.application.usecase.user.updateuser

import com.iaratech2025.remake2026.domain.model.Email
import com.iaratech2025.remake2026.domain.model.User
import java.util.UUID

data class UpdateUserCommand(
    val id: UUID,
    val name: String? = null,
    val email: Email? = null,
    val role: String? = null,
    val manager: User? = null,
    val permissions: List<User.Permission>? = null,
    val isActive: Boolean? = null,
    val factoryId: UUID? = null,
    val gender: User.Gender? = null
)