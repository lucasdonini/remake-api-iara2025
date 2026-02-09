package com.interdisciplinar2025.remake.domain

import com.interdisciplinar2025.remake.common.builders.AddressBuilder
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
}