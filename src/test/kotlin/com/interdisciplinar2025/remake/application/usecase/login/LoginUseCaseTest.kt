package com.interdisciplinar2025.remake.application.usecase.login

import com.interdisciplinar2025.remake.application.port.PasswordHasher
import com.interdisciplinar2025.remake.common.builders.UserBuilder
import com.interdisciplinar2025.remake.domain.exception.NotFoundException.UserNotFoundByEmail
import com.interdisciplinar2025.remake.domain.model.Email
import com.interdisciplinar2025.remake.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Clock

class LoginUseCaseTest {

    private val repository: UserRepository = mockk()
    private val hasher: PasswordHasher = mockk()
    private lateinit var sut: LoginHandler

    @BeforeEach
    fun setup() {
        sut = LoginHandler(repository, hasher)
    }

    @Test
    fun `if User is not found, throws UserNotFoundException`() {
        val command = LoginCommand(Email.create("example@email.com"), "12345678")
        every { repository.getByEmail(any()) } returns null

        assertThrows<UserNotFoundByEmail> { sut.handle(command) }
    }

    @Test
    fun `if Password is incorrect, returns false`() {
        val user = UserBuilder(Clock.systemUTC()).build()
        val command = LoginCommand(user.email, "wrongpassword")
        every { repository.getByEmail(user.email.value) } returns user
        every { hasher.compare(command.password, user.passwordHash) } returns false

        assert(!sut.handle(command))
    }

    @Test
    fun `if Password is correct, returns true`() {
        val user = UserBuilder(Clock.systemUTC()).build()
        val command = LoginCommand(user.email, "correctpassword")
        every { repository.getByEmail(user.email.value) } returns user
        every { hasher.compare(command.password, user.passwordHash) } returns true

        assert(sut.handle(command))
    }
}