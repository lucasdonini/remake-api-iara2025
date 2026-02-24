package com.iaratech2025.remake2026.application.usecase.plan.list

import com.iaratech2025.remake2026.domain.model.Plan
import com.iaratech2025.remake2026.domain.repository.PlanRepository
import com.iaratech2025.remake2026.domain.repository.pagination.PageResult
import org.springframework.stereotype.Component

@Component
class ListPlanHandler(val repository: PlanRepository) {
    fun handle(query: ListPlanQuery): PageResult<Plan> =
        repository.findAll(filter = query.filter, pagination = query.pagination)
}