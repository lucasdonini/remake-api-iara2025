package com.interdisciplinar2025.remake.application.usecase.listusers

import com.interdisciplinar2025.remake.application.shared.pagination.PageResult
import com.interdisciplinar2025.remake.application.shared.pagination.Pagination
import com.interdisciplinar2025.remake.domain.filter.UserFilter
import com.interdisciplinar2025.remake.domain.model.User
import com.interdisciplinar2025.remake.domain.repository.UserRepository
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