package com.iaratech2025.remake2026.application.usecase.user.login

import com.iaratech2025.remake2026.application.port.PasswordHasher
import com.iaratech2025.remake2026.application.usecase.user.login.LoginCommand
import com.iaratech2025.remake2026.application.usecase.user.login.LoginHandler
import com.iaratech2025.remake2026.common.builders.UserBuilder
import com.iaratech2025.remake2026.domain.exception.NotFoundException.UserNotFoundByEmailException
import com.iaratech2025.remake2026.domain.model.Email
import com.iaratech2025.remake2026.domain.repository.UserRepository
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

        assertThrows<UserNotFoundByEmailException> { sut.handle(command) }
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