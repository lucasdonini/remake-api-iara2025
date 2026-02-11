package com.iaratech2025.remake2026.application.usecase.listusers

import com.iaratech2025.remake2026.domain.repository.pagination.Pagination
import com.iaratech2025.remake2026.domain.filter.UserFilter
import com.iaratech2025.remake2026.domain.model.User
import com.iaratech2025.remake2026.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ListUsersUseCaseTests {

    private val repository: UserRepository = mockk()
    private lateinit var sut: ListUsersHandler

    @BeforeEach
    fun setup() {
        sut = ListUsersHandler(repository)
    }

    @Test
    fun `when handle is called, calls repository with correct filter and pagination`() {
        every { repository.findAll(any()) } returns listOf()
        val filter = UserFilter(gender = User.Gender.FEMALE)
        val pagination = Pagination(page = 1u, size = 10u)

        val result = sut.handle(filter, pagination)

        verify(exactly = 1) { repository.findAll(filter) }
        assert(result.content.isEmpty())
        assert(result.totalElements == 0uL)
        assert(result.page == pagination.page)
        assert(result.size == pagination.size)
    }
}