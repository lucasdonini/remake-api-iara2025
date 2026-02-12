package com.iaratech2025.remake2026.domain

import com.iaratech2025.remake2026.common.builders.FactoryBuilder
import com.iaratech2025.remake2026.domain.model.Factory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FactoryTests {
    private lateinit var sut: Factory

    @BeforeEach
    fun setUp() {
        sut = FactoryBuilder().build()
    }

    @Test
    fun `should create Factory with valid data`() {
        val factory = Factory.create(
            cnpj = sut.cnpj,
            isActive = sut.isActive,
            email = sut.email,
            enterpriseName = sut.enterpriseName,
            industrySector = sut.industrySector,
            hiredPlan = sut.hiredPlan,
            address = sut.address
        )

        assert(factory.cnpj == sut.cnpj)
        assert(factory.isActive == sut.isActive)
        assert(factory.email == sut.email)
        assert(factory.enterpriseName == sut.enterpriseName)
        assert(factory.industrySector == sut.industrySector)
        assert(factory.hiredPlan == sut.hiredPlan)
        assert(factory.address == sut.address)
    }

    @Test
    fun `if enterprise name is blank, should throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> {
            Factory.create(
                cnpj = sut.cnpj,
                isActive = sut.isActive,
                email = sut.email,
                enterpriseName = "   ",
                industrySector = sut.industrySector,
                hiredPlan = sut.hiredPlan,
                address = sut.address
            )
        }
    }

    @Test
    fun `if industry sector is blank, should throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> {
            Factory.create(
                cnpj = sut.cnpj,
                isActive = sut.isActive,
                email = sut.email,
                enterpriseName = sut.enterpriseName,
                industrySector = "   ",
                hiredPlan = sut.hiredPlan,
                address = sut.address
            )
        }
    }

    @Test
    fun `if Enterprise Name is blank, should fail to update object`() {
        assertThrows<IllegalArgumentException> {
            sut.enterpriseName = "  "
        }
    }

    @Test
    fun `if Industry Sector is blank, should fail to update object`() {
        assertThrows<IllegalArgumentException> {
            sut.industrySector = "  "
        }
    }

    @Test
    fun `if all data is correct, updates object successfully`() {
        val newEnterpriseName = "New Enterprise Name"
        val newIndustrySector = "New Industry Sector"

        sut.enterpriseName = newEnterpriseName
        sut.industrySector = newIndustrySector

        assert(sut.enterpriseName == newEnterpriseName)
        assert(sut.industrySector == newIndustrySector)
    }
}