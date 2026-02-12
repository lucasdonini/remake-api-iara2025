package com.iaratech2025.remake2026.application.usecase.createuser

import com.iaratech2025.remake2026.application.port.PasswordHasher
import com.iaratech2025.remake2026.common.builders.UserBuilder
import com.iaratech2025.remake2026.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock

class CreateUserUseCaseTests {

    private val repository: UserRepository = mockk()
    private val hasher: PasswordHasher = mockk()
    private lateinit var sut: CreateUserHandler

    @BeforeEach
    fun setup() {
        sut = CreateUserHandler(repository, hasher)
    }

    private fun createCommand(): CreateUserCommand {
        val validUser = UserBuilder(Clock.systemUTC()).build()
        return CreateUserCommand(
            name = validUser.name,
            birthday = validUser.birthday,
            email = validUser.email,
            password = "plaintextpassword",
            role = validUser.role,
            manager = validUser.manager,
            factoryId = validUser.employerFactoryId,
            gender = validUser.gender
        )
    }

    @Test
    fun `should create user successfully`() {
        val command = createCommand()
        val hashedPassword = "hashedpassword"
        every { hasher.hash(command.password) } returns hashedPassword
        every { repository.save(any()) } answers { firstArg() }

        val result = sut.handle(command)

        assert(result.name == command.name)
        assert(result.birthday == command.birthday)
        assert(result.email == command.email)
        assert(result.passwordHash == hashedPassword)
        assert(result.role == command.role)
        assert(result.manager == command.manager)
        assert(result.employerFactoryId == command.factoryId)
        assert(result.gender == command.gender)
        verify(exactly = 1) { hasher.hash(command.password) }
        verify(exactly = 1) { repository.save(any()) }
    }
}