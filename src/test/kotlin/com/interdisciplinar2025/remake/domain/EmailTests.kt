package com.interdisciplinar2025.remake.domain

import com.interdisciplinar2025.remake.domain.model.Email
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EmailTests {
    @ParameterizedTest
    @ValueSource(strings = ["exampleemail.com", "example@.com", "example@com", "@example.com", "example@com.", "example@"])
    fun `shouldn't create Email`(value: String) {
        assertThrows<IllegalArgumentException> {
            Email.create(value)
        }
    }

    @Test
    fun `should create Email`() {
        val value = "example@email.com"
        val email = Email.create(value)

        assert(email.value == value)
    }
}