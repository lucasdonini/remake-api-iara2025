package com.iaratech2025.remake2026.domain

import com.iaratech2025.remake2026.common.builders.PlanBuilder
import com.iaratech2025.remake2026.domain.model.Plan
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Period

class PlanTests {
    private lateinit var sut: Plan

    @BeforeEach
    fun setUp() {
        sut = PlanBuilder.builder().build()
    }

    @Test
    fun `when Name is blank, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Plan.create(
                name = " ",
                price = sut.price,
                description = sut.description,
                duration = sut.duration
            )
        }
    }

    @Test
    fun `when Price is negative, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Plan.create(
                name = sut.name,
                price = -1.0,
                description = sut.description,
                duration = sut.duration
            )
        }
    }

    @Test
    fun `when duration is less than a month, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Plan.create(
                name = sut.name,
                price = sut.price,
                description = sut.description,
                duration = Period.ofDays(29)
            )
        }
    }

    @Test
    fun `when all data is correct, creates object`() {
        val plan = Plan.create(
            name = sut.name,
            price = sut.price,
            description = sut.description,
            duration = sut.duration
        )

        assert(plan.name == sut.name)
        assert(plan.price == sut.price)
        assert(plan.description == sut.description)
        assert(plan.duration == sut.duration)
    }
}