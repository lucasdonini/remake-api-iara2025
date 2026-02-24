package com.iaratech2025.remake2026.application.usecase.superadm.list

import com.iaratech2025.remake2026.domain.filter.SuperAdmFilter
import com.iaratech2025.remake2026.domain.model.SuperAdmin
import com.iaratech2025.remake2026.domain.repository.SuperAdmRepository
import com.iaratech2025.remake2026.domain.repository.pagination.PageResult
import com.iaratech2025.remake2026.domain.repository.pagination.Pagination
import com.iaratech2025.remake2026.domain.repository.pagination.Sort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ListSuperAdmUseCaseTests {

    private val repository: SuperAdmRepository = mockk()
    private lateinit var sut: ListSuperAdmHandler

    @BeforeEach
    fun setup() {
        sut = ListSuperAdmHandler(repository)
    }

    @Test
    fun `when handle is called, calls repository with correct filter and pagination`() {
        val filter = SuperAdmFilter(job = "Backend developer")
        val pagination = Pagination(page = 1u, size = 10u, Sort(SuperAdmin.SuperAdmSortableField.NAME))
        val query = ListSuperAdmQuery(filter = filter, pagination = pagination)
        every { repository.findAll(filter, pagination) } returns PageResult(
            content = emptyList(),
            totalElements = 0uL,
            page = pagination.page,
            size = pagination.size
        )

        val result = sut.handle(query)

        verify(exactly = 1) { repository.findAll(filter, pagination) }
        assert(result.content.isEmpty())
        assert(result.totalElements == 0uL)
        assert(result.page == pagination.page)
        assert(result.size == pagination.size)
    }
}