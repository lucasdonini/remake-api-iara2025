package com.iaratech2025.remake2026.domain.repository

import com.iaratech2025.remake2026.domain.filter.SuperAdmFilter
import com.iaratech2025.remake2026.domain.model.SuperAdmin
import com.iaratech2025.remake2026.domain.repository.pagination.PageResult
import com.iaratech2025.remake2026.domain.repository.pagination.Pagination

interface SuperAdmRepository {
    fun findAll(filter: SuperAdmFilter? = null, pagination: Pagination<SuperAdmin>): PageResult<SuperAdmin>
    fun save(superAdmin: SuperAdmin): SuperAdmin
    fun existsByEmail(email: String): Boolean
}