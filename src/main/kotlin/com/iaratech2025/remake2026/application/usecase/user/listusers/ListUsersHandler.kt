package com.iaratech2025.remake2026.application.usecase.user.listusers

import com.iaratech2025.remake2026.domain.filter.UserFilter
import com.iaratech2025.remake2026.domain.model.User
import com.iaratech2025.remake2026.domain.repository.UserRepository
import com.iaratech2025.remake2026.domain.repository.pagination.PageResult
import com.iaratech2025.remake2026.domain.repository.pagination.Pagination
import org.springframework.stereotype.Component

@Component
class ListUsersHandler(val repository: UserRepository) {
    fun handle(filter: UserFilter, pagination: Pagination<User>): PageResult<User> =
        repository.findAll(filter, pagination)
}