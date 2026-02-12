package com.iaratech2025.remake2026.application.usecase.superadm.list

import com.iaratech2025.remake2026.domain.filter.SuperAdmFilter
import com.iaratech2025.remake2026.domain.model.SuperAdmin
import com.iaratech2025.remake2026.domain.repository.pagination.Pagination

data class ListSuperAdmQuery(val filter: SuperAdmFilter?, val pagination: Pagination<SuperAdmin>)