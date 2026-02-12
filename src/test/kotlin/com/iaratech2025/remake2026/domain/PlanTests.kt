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

    @Test
    fun `if Name is blank, should fail to update object`() {
        assertThrows<IllegalArgumentException> {
            sut.name = " "
        }
    }

    @Test
    fun `if Price is negative, should fail to update object`() {
        assertThrows<IllegalArgumentException> {
            sut.price = -1.0
        }
    }

    @Test
    fun `if duration is less than a month, should fail to update object`() {
        assertThrows<IllegalArgumentException> {
            sut.duration = Period.ofDays(29)
        }
    }

    @Test
    fun `if all data is correct, should update object`() {
        val newName = "New Plan Name"
        val newPrice = 199.99
        val newDescription = "Updated description for the plan."
        val newDuration = Period.ofMonths(6)

        sut.name = newName
        sut.price = newPrice
        sut.description = newDescription
        sut.duration = newDuration

        assert(sut.name == newName)
        assert(sut.price == newPrice)
        assert(sut.description == newDescription)
        assert(sut.duration == newDuration)
    }
}
