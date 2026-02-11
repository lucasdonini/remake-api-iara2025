package com.interdisciplinar2025.remake.domain.filter

import com.interdisciplinar2025.remake.domain.model.User
import java.util.UUID

data class UserFilter(
    val gender: User.Gender? = null,
    val job: String? = null,
    val isActive: Boolean? = null,
    val managerEmail: String? = null,
    val factoryId: UUID? = null
)