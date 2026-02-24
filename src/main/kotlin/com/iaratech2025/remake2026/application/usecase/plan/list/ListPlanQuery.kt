package com.iaratech2025.remake2026.application.usecase.plan.list

import com.iaratech2025.remake2026.domain.filter.PlanFilter
import com.iaratech2025.remake2026.domain.model.Plan
import com.iaratech2025.remake2026.domain.repository.pagination.Pagination

data class ListPlanQuery(val filter: PlanFilter?, val pagination: Pagination<Plan>)
