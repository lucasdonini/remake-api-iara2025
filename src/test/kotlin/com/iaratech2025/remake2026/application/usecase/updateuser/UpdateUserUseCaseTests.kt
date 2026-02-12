package com.iaratech2025.remake2026.application.usecase.updateuser

import com.iaratech2025.remake2026.common.builders.UserBuilder
import com.iaratech2025.remake2026.domain.exception.NotFoundException
import com.iaratech2025.remake2026.domain.model.Email
import com.iaratech2025.remake2026.domain.repository.UserRepository
import com.iaratech2025.remake2026.domain.shared.EMPTY_UUID
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Clock
import java.time.ZoneOffset
import java.time.Instant

class UpdateUserUseCaseTests {

    val fixedClock: Clock = Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC)
    val repository: UserRepository = mockk()
    lateinit var sut: UpdateUserHandler

    @BeforeEach
    fun setup() {
        sut = UpdateUserHandler(repository)
    }

    @Test
    fun `if user exists, should update user successfully`() {
        val user = UserBuilder(fixedClock).build()
        val command = UpdateUserCommand(
            id = user.id,
            name = "New Name",
            email = Email.create("newemail@email.com"),
            role = "New Role",
            manager = UserBuilder(fixedClock).build(),
            permissions = null,
            isActive = null,
            factoryId = null,
            gender = null
        )
        every { repository.findById(user.id) } returns user

        val result = sut.handle(command)

        assert(result.name == command.name)
        assert(result.email == command.email)
        assert(result.role == command.role)
        assert(result.manager == command.manager)
        assert(result.permissions == user.permissions)
        assert(result.isActive == user.isActive)
        assert(result.employerFactoryId == user.employerFactoryId)
        assert(result.gender == user.gender)
        verify(exactly = 1) { repository.findById(user.id) }
    }

    @Test
    fun `if user does not exist, should throw NotFoundException`() {
        every { repository.findById(any()) } returns null
        val command = UpdateUserCommand(EMPTY_UUID)

        assertThrows<NotFoundException.UserNotFoundById> { sut.handle(command) }
        verify(exactly = 1) { repository.findById(command.id) }
    }
}