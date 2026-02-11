package com.interdisciplinar2025.remake.domain.repository

import com.interdisciplinar2025.remake.domain.model.User

interface UserRepository {
    fun getByEmail(email: String): User?
}