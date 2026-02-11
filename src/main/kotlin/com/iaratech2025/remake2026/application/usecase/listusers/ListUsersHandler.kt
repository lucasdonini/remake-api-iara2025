package com.iaratech2025.remake2026.application.usecase.listusers

import com.iaratech2025.remake2026.application.shared.pagination.PageResult
import com.iaratech2025.remake2026.application.shared.pagination.Pagination
import com.iaratech2025.remake2026.domain.filter.UserFilter
import com.iaratech2025.remake2026.domain.model.User
import com.iaratech2025.remake2026.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class ListUsersHandler(val repository: UserRepository) {
    fun handle(filter: UserFilter, pagination: Pagination): PageResult<User> {
        val allUsers = repository.findAll(filter)
        val paged = allUsers
            .drop(pagination.offset().toInt())
            .take(pagination.size.toInt())

        return PageResult(
            content = paged,
            totalElements = allUsers.size.toULong(),
            page = pagination.page,
            size = pagination.size
        )
    }
}