package com.iaratech2025.remake2026.domain.repository

import com.iaratech2025.remake2026.domain.filter.UserFilter
import com.iaratech2025.remake2026.domain.model.User

interface UserRepository {
    fun getByEmail(email: String): User?
    fun findAll(filter: UserFilter? = null): List<User>
}