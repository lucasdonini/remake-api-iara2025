package com.iaratech2025.remake2026.domain.filter

import com.iaratech2025.remake2026.domain.model.User
import java.util.*

data class UserFilter(
    val gender: User.Gender? = null,
    val job: String? = null,
    val isActive: Boolean? = null,
    val managerEmail: String? = null,
    val factoryId: UUID? = null
)