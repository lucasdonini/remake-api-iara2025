package com.interdisciplinar2025.remake.domain

import com.interdisciplinar2025.remake.common.builders.SuperAdminBuilder
import com.interdisciplinar2025.remake.domain.model.SuperAdmin
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SuperAdminTest {
    private lateinit var sut: SuperAdmin

    @BeforeEach
    fun setUp() {
        sut = SuperAdminBuilder().build()
    }

    @Test
    fun `should create a valid SuperAdmin`() {
        val adm = SuperAdmin.create(
            name = sut.name,
            email = sut.email,
            job = sut.job,
            passwordHash = sut.passwordHash
        )

        assert(adm.name == sut.name)
        assert(adm.email == sut.email)
        assert(adm.job == sut.job)
        assert(adm.passwordHash == sut.passwordHash)
    }

    @Test
    fun `if Name is blank, shouldn't create an Admin`() {
        assertThrows<IllegalArgumentException> {
            SuperAdmin.create(
                name = "   ",
                email = sut.email,
                job = sut.job,
                passwordHash = sut.passwordHash
            )
        }
    }

    @Test
    fun `if Job is blank, shouldn't create an Admin`() {
        assertThrows<IllegalArgumentException> {
            SuperAdmin.create(
                name = sut.name,
                email = sut.email,
                job = "   ",
                passwordHash = sut.passwordHash
            )
        }
    }

    @Test
    fun `if Password is blank, shouldn't create an Admin`() {
        assertThrows<IllegalArgumentException> {
            SuperAdmin.create(
                name = sut.name,
                email = sut.email,
                job = sut.job,
                passwordHash = "   "
            )
        }
    }
}