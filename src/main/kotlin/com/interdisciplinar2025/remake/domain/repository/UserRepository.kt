package com.interdisciplinar2025.remake.domain.repository

import com.interdisciplinar2025.remake.domain.filter.UserFilter
import com.interdisciplinar2025.remake.domain.model.User

interface UserRepository {
    fun getByEmail(email: String): User?
    fun findAll(filter: UserFilter? = null): List<User>
}