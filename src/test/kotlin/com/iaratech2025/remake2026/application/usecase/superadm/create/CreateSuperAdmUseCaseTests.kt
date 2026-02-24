package com.iaratech2025.remake2026.application.usecase.superadm.create

import com.iaratech2025.remake2026.application.exception.ConflictException
import com.iaratech2025.remake2026.application.port.PasswordHasher
import com.iaratech2025.remake2026.domain.model.Email
import com.iaratech2025.remake2026.domain.repository.SuperAdmRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class CreateSuperAdmUseCaseTests {

    private val repository: SuperAdmRepository = mockk()
    private val hasher: PasswordHasher = mockk()
    private lateinit var sut: CreateSuperAdmHandler

    private fun validCommand(): CreateSuperAdmCommand = CreateSuperAdmCommand(
        name = "John Doe",
        job = "CTO",
        email = Email.create("john.doe@enterprise.org"),
        password = "password123"
    )

    @BeforeEach
    fun setUp() {
        sut = CreateSuperAdmHandler(repository, hasher)
    }

    @Test
    fun `should create super admin successfully`() {
        val command = validCommand()
        every { repository.existsByEmail(command.email.value) } returns false
        every { hasher.hash(command.password) } returns "hashedPassword"
        every { repository.save(any()) } returnsArgument 0

        val result = sut.handle(command)

        assert(result.name == command.name)
        assert(result.job == command.job)
        assert(result.email == command.email)
        assert(result.passwordHash == "hashedPassword")
        verify(exactly = 1) { repository.existsByEmail(command.email.value) }
        verify(exactly = 1) { hasher.hash(command.password) }
        verify(exactly = 1) { repository.save(any()) }
    }

    @Test
    fun `should throw exception when email already exists`() {
        val command = validCommand()
        every { repository.existsByEmail(command.email.value) } returns true

        assertThrows<ConflictException.EmailAlreadyExists> { sut.handle(command) }
        verify(exactly = 1) { repository.existsByEmail(command.email.value) }
        verify(exactly = 0) { hasher.hash(any()) }
        verify(exactly = 0) { repository.save(any()) }
    }

    @Test
    fun `if repository throws exception, it should be propagated`() {
        val command = validCommand()
        every { repository.existsByEmail(command.email.value) } returns false
        every { hasher.hash(command.password) } returns "hashedPassword"
        every { repository.save(any()) } throws RuntimeException("Database error")

        assertThrows<RuntimeException> { sut.handle(command) }
        verify(exactly = 1) { repository.existsByEmail(command.email.value) }
        verify(exactly = 1) { hasher.hash(command.password) }
        verify(exactly = 1) { repository.save(any()) }
    }

    @Test
    fun `if hasher throws exception, it should be propagated`() {
        val command = validCommand()
        every { repository.existsByEmail(command.email.value) } returns false
        every { hasher.hash(command.password) } throws RuntimeException("Hashing error")

        assertThrows<RuntimeException> { sut.handle(command) }
        verify(exactly = 1) { repository.existsByEmail(command.email.value) }
        verify(exactly = 1) { hasher.hash(command.password) }
        verify(exactly = 0) { repository.save(any()) }
    }
}