package com.iaratech2025.remake2026.domain

import com.iaratech2025.remake2026.common.builders.UserBuilder
import com.iaratech2025.remake2026.domain.model.User
import com.iaratech2025.remake2026.domain.shared.EMPTY_UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class UserTests {
    private val fixedClock: Clock = Clock.fixed(Instant.parse("2026-01-01T10:00:00Z"), ZoneOffset.UTC)
    private lateinit var sut: User

    @BeforeEach
    fun setUp() {
        sut = UserBuilder(clock = fixedClock).build()
    }

    @Test
    fun `should create a valid User`() {
        val user = User.create(
            manager = sut.manager,
            name = sut.name,
            gender = sut.gender,
            birthday = sut.birthday,
            email = sut.email,
            passwordHash = sut.passwordHash,
            role = sut.role,
            permissions = sut.permissions,
            createdAt = sut.createdAt,
            isActive = sut.isActive,
            employerFactoryId = sut.employerFactoryId,
            clock = fixedClock
        )

        assert(user.manager == sut.manager)
        assert(user.name == sut.name)
        assert(user.gender == sut.gender)
        assert(user.birthday == sut.birthday)
        assert(user.email == sut.email)
        assert(user.passwordHash == sut.passwordHash)
        assert(user.role == sut.role)
        assert(user.permissions == sut.permissions)
        assert(user.createdAt == sut.createdAt)
        assert(user.isActive == sut.isActive)
        assert(user.employerFactoryId == sut.employerFactoryId)
    }

    @Test
    fun `if Name is blank, shouldn't create an User`() {
        assertThrows<IllegalArgumentException> {
            User.create(
                manager = sut.manager,
                name = " ",
                gender = sut.gender,
                birthday = sut.birthday,
                email = sut.email,
                passwordHash = sut.passwordHash,
                role = sut.role,
                permissions = sut.permissions,
                createdAt = sut.createdAt,
                isActive = sut.isActive,
                employerFactoryId = sut.employerFactoryId,
                clock = fixedClock
            )
        }
    }

    @Test
    fun `if Age is less than 16, shouldn't create an User`() {
        assertThrows<IllegalArgumentException> {
            User.create(
                manager = sut.manager,
                name = sut.name,
                gender = sut.gender,
                birthday = LocalDate.now(fixedClock).minusYears(12),
                email = sut.email,
                passwordHash = sut.passwordHash,
                role = sut.role,
                permissions = sut.permissions,
                createdAt = sut.createdAt,
                isActive = sut.isActive,
                employerFactoryId = sut.employerFactoryId,
                clock = fixedClock
            )
        }
    }

    @Test
    fun `if Password is blank, shouldn't create an User`() {
        assertThrows<IllegalArgumentException> {
            User.create(
                manager = sut.manager,
                name = sut.name,
                gender = sut.gender,
                birthday = sut.birthday,
                email = sut.email,
                passwordHash = " ",
                role = sut.role,
                permissions = sut.permissions,
                createdAt = sut.createdAt,
                isActive = sut.isActive,
                employerFactoryId = sut.employerFactoryId,
                clock = fixedClock
            )
        }
    }

    @Test
    fun `if Role is blank, shouldn't create an User`() {
        assertThrows<IllegalArgumentException> {
            User.create(
                manager = sut.manager,
                name = sut.name,
                gender = sut.gender,
                birthday = sut.birthday,
                email = sut.email,
                passwordHash = sut.passwordHash,
                role = " ",
                permissions = sut.permissions,
                createdAt = sut.createdAt,
                isActive = sut.isActive,
                employerFactoryId = sut.employerFactoryId,
                clock = fixedClock
            )
        }
    }

    @Test
    fun `if Creation Date is in the future, shouldn't create an User`() {
        assertThrows<IllegalArgumentException> {
            User.create(
                manager = sut.manager,
                name = sut.name,
                gender = sut.gender,
                birthday = sut.birthday,
                email = sut.email,
                passwordHash = sut.passwordHash,
                role = sut.role,
                permissions = sut.permissions,
                createdAt = LocalDateTime.now(fixedClock).plusDays(2),
                isActive = sut.isActive,
                employerFactoryId = sut.employerFactoryId,
                clock = fixedClock
            )
        }
    }

    @Test
    fun `if no Factory Id is passed to factory method, shouldn't create an User`() {
        assertThrows<IllegalArgumentException> {
            User.create(
                manager = sut.manager,
                name = sut.name,
                gender = sut.gender,
                birthday = sut.birthday,
                email = sut.email,
                passwordHash = sut.passwordHash,
                role = sut.role,
                permissions = sut.permissions,
                createdAt = sut.createdAt,
                isActive = sut.isActive,
                employerFactoryId = EMPTY_UUID,
                fixedClock
            )
        }
    }
}
