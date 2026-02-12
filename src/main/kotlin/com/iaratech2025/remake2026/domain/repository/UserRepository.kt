package com.iaratech2025.remake2026.domain.repository

import com.iaratech2025.remake2026.domain.filter.UserFilter
import com.iaratech2025.remake2026.domain.model.User
import com.iaratech2025.remake2026.domain.repository.pagination.PageResult
import com.iaratech2025.remake2026.domain.repository.pagination.Pagination
import java.util.UUID

interface UserRepository {
    fun getByEmail(email: String): User?
    fun findAll(filter: UserFilter? = null, pagination: Pagination<User>): PageResult<User>
    fun save(user: User): User
    fun findById(id: UUID): User?
    fun deleteById(id: UUID)
}