package com.interdisciplinar2025.remake.domain

import com.interdisciplinar2025.remake.domain.model.CNPJ
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CNPJTests {
    @Test
    fun `should create CNPJ with valid format`() {
        val value = "00.000.000/0000-00"
        val cnpj = CNPJ.create(value)
        assert(cnpj.value == value)
    }

    @Test
    fun `if CNPJ is blank, should throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> { CNPJ.create("  ") }
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "00000000000000",
        "00.000.000/0000-0",
        "00.000.000/0000-000",
        "00.000.000/000-00",
        "00.000.000/00000-00",
        "00.000.00/0000-00",
        "00.000.0000/0000-00",
        "00.00.000/0000-00",
        "00.0000.000/0000-00",
        "0.000.000/0000-00",
        "000.000.000/0000-00",
        "00000.000/0000-00",
        "00.000000/0000-00",
        "00.000.0000000-00",
        "00.000.000/000000",
        "0a.000.000/0000-00",
        "00.0a0.000/0000-00",
        "00.000.0a0/0000-00",
        "00.000.000/000a-00",
        "00.000.000/0000-0a"
    ])
    fun `if CNPJ format is invalid, should throw IllegalArgumentException`(value: String) {
        assertThrows<IllegalArgumentException> { CNPJ.create(value) }
    }
}