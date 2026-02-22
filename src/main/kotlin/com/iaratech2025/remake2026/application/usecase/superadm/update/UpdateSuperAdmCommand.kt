package com.iaratech2025.remake2026.application.usecase.superadm.update

import com.iaratech2025.remake2026.domain.model.Email
import java.util.UUID

data class UpdateSuperAdmCommand(
    val id: UUID,
    val name: String? = null,
    val email: Email? = null,
    val job: String? = null,
    val currentPassword: String? = null,
    val newPassword: String? = null
)
