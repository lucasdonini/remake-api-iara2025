package com.interdisciplinar2025.remake.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CEPTests {
    @ParameterizedTest
    @ValueSource(strings = ["1234567", "123456789"])
    fun `when CEP length is not 8, fails to create`(value: String) {
        assertThrows<IllegalArgumentException> { CEP.create(value) }
    }

    @Test
    fun `when CEP length is 8, creates object`() {
        val cepValue = "12345678"
        val cep = CEP.create(cepValue)

        assert(cepValue == cep.value)
    }
}