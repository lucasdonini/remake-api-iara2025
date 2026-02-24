package com.iaratech2025.remake2026.domain.repository

import com.iaratech2025.remake2026.domain.filter.PlanFilter
import com.iaratech2025.remake2026.domain.model.Plan
import com.iaratech2025.remake2026.domain.repository.pagination.PageResult
import com.iaratech2025.remake2026.domain.repository.pagination.Pagination

interface PlanRepository {
    fun findAll(pagination: Pagination<Plan>, filter: PlanFilter? = null): PageResult<Plan>
}
