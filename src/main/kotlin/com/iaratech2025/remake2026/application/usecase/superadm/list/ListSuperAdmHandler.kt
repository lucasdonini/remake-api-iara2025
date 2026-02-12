package com.iaratech2025.remake2026.application.usecase.superadm.list

import com.iaratech2025.remake2026.domain.model.SuperAdmin
import com.iaratech2025.remake2026.domain.repository.SuperAdmRepository
import com.iaratech2025.remake2026.domain.repository.pagination.PageResult
import org.springframework.stereotype.Component

@Component
class ListSuperAdmHandler(val repository: SuperAdmRepository) {
    fun handle(query: ListSuperAdmQuery): PageResult<SuperAdmin> = repository.findAll(filter = query.filter, pagination = query.pagination)
}