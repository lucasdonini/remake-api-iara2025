package com.iaratech2025.remake2026.domain

import com.iaratech2025.remake2026.common.builders.AddressBuilder
import com.iaratech2025.remake2026.domain.model.Address
import com.iaratech2025.remake2026.domain.model.CEP
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AddressTests {
    private lateinit var sut: Address

    @BeforeEach
    fun setUp() {
        sut = AddressBuilder.builder().build()
    }

    @Test
    fun `when State is blank, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Address.create(
                state = " ",
                city = sut.city,
                street = sut.street,
                number = sut.number,
                complement = sut.complement,
                cep = sut.cep
            )
        }
    }

    @Test
    fun `when City is blank, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Address.create(
                state = sut.state,
                city = " ",
                street = sut.street,
                number = sut.number,
                complement = sut.complement,
                cep = sut.cep
            )
        }
    }

    @Test
    fun `when Street is blank, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Address.create(
                state = sut.state,
                city = sut.city,
                street = " ",
                number = sut.number,
                complement = sut.complement,
                cep = sut.cep
            )
        }
    }

    @Test
    fun `when State is blank, fails to update object`() {
        assertThrows<IllegalArgumentException> {
            sut.state = " "
        }
    }

    @Test
    fun `when City is blank, fails to update object`() {
        assertThrows<IllegalArgumentException> {
            sut.city = " "
        }
    }

    @Test
    fun `when Street is blank, fails to update object`() {
        assertThrows<IllegalArgumentException> {
            sut.street = " "
        }
    }

    @Test
    fun `when all data is correct, updates object successfully`() {
        val newState = "New State"
        val newCity = "New City"
        val newStreet = "New Street"
        val newNumber = 123u
        val newComplement = "Apt 101"
        val newCep = CEP.create("12345678")

        sut.state = newState
        sut.city = newCity
        sut.street = newStreet
        sut.number = newNumber
        sut.complement = newComplement
        sut.cep = newCep

        assert(sut.state == newState)
        assert(sut.city == newCity)
        assert(sut.street == newStreet)
        assert(sut.number == newNumber)
        assert(sut.complement == newComplement)
        assert(sut.cep == newCep)
    }
}