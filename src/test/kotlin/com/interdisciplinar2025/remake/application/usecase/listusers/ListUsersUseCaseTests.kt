package com.interdisciplinar2025.remake.application.usecase.listusers

import com.interdisciplinar2025.remake.application.shared.pagination.Pagination
import com.interdisciplinar2025.remake.domain.filter.UserFilter
import com.interdisciplinar2025.remake.domain.model.User
import com.interdisciplinar2025.remake.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.collections.listOf

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