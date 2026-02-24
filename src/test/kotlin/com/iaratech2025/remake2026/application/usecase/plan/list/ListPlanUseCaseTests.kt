package com.iaratech2025.remake2026.application.usecase.plan.list

import com.iaratech2025.remake2026.domain.filter.PlanFilter
import com.iaratech2025.remake2026.domain.model.Plan
import com.iaratech2025.remake2026.domain.repository.PlanRepository
import com.iaratech2025.remake2026.domain.repository.pagination.PageResult
import com.iaratech2025.remake2026.domain.repository.pagination.Pagination
import com.iaratech2025.remake2026.domain.repository.pagination.Sort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.time.Period
import kotlin.test.Test

class ListPlanUseCaseTests {
    private val repository: PlanRepository = mockk()
    private lateinit var sut: ListPlanHandler

    @BeforeEach
    fun setUp() {
        sut = ListPlanHandler(repository)
    }

    @Test
    fun `when handle is called, calls repository with correct filter and pagination`() {
        val filter = PlanFilter(name = "my plan", value = 10.0, duration = Period.ofMonths(1))
        val pagination = Pagination(
            page = 1u,
            size = 10u,
            Sort(field = Plan.PlanSortableField.PRICE, direction = Sort.Direction.ASC)
        )
        val query = ListPlanQuery(filter = filter, pagination = pagination)
        every { repository.findAll(filter = filter, pagination = pagination) } returns PageResult(
            content = emptyList(),
            totalElements = 0uL,
            page = pagination.page,
            size = pagination.size
        )

        val result = sut.handle(query)

        verify(exactly = 1) { repository.findAll(filter = filter, pagination = pagination) }
        assert(result.content.isEmpty())
        assert(result.totalElements == 0uL)
        assert(result.page == pagination.page)
        assert(result.size == pagination.size)
    }

    @Test
    fun `if repository throws exception, it should be propagated`() {
        val pagination = Pagination(
            page = 1u,
            size = 10u,
            sort = Sort(field = Plan.PlanSortableField.PRICE, direction = Sort.Direction.ASC)
        )
        val query = ListPlanQuery(filter = PlanFilter(), pagination = pagination)
        every { repository.findAll(filter = query.filter, pagination = pagination) } throws RuntimeException()

        assertThrows<RuntimeException> { sut.handle(query) }

        verify(exactly = 1) { repository.findAll(filter = query.filter, pagination = pagination) }
    }
}

