package com.iaratech2025.remake2026.application.usecase.superadm.update

import com.iaratech2025.remake2026.application.exception.BadRequestException
import com.iaratech2025.remake2026.application.exception.UnauthorizedException
import com.iaratech2025.remake2026.application.port.PasswordHasher
import com.iaratech2025.remake2026.common.builders.SuperAdminBuilder
import com.iaratech2025.remake2026.domain.model.Email
import com.iaratech2025.remake2026.domain.repository.SuperAdmRepository
import com.iaratech2025.remake2026.domain.shared.EMPTY_UUID
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class UpdateSuperAdmUseCaseTests {
    private val repository: SuperAdmRepository = mockk()
    private val hasher: PasswordHasher = mockk()
    private lateinit var sut: UpdateSuperAdmHandler

    @BeforeEach
    fun setUp() {
        sut = UpdateSuperAdmHandler(repository, hasher)
    }

    @Test
    fun `should update successfully`() {
        val superAdm = SuperAdminBuilder().build()
        val oldPasswordHash = superAdm.passwordHash
        val command = UpdateSuperAdmCommand(
            id = superAdm.id,
            name = "new name",
            email = Email.create("new.email@enterprise.com"),
            job = "new job",
            currentPassword = "password",
            newPassword = "new password"
        )

        every { repository.findById(command.id) } returns superAdm
        every { hasher.compare(password = command.currentPassword!!, hashed = superAdm.passwordHash) } returns true
        every { hasher.hash(command.newPassword!!) } returns "*****"

        val result = sut.handle(command)

        assert(result.name == command.name)
        assert(result.email == command.email)
        assert(result.job == command.job)
        assert(result.passwordHash != oldPasswordHash)
        assert(result.passwordHash != command.newPassword)
        verify(exactly = 1) { repository.findById(command.id) }
        verify(exactly = 1) { hasher.compare(password = command.currentPassword!!, hashed = oldPasswordHash) }
        verify(exactly = 1) { hasher.hash(command.newPassword!!) }
    }

    @Test
    fun `if findById throws exception, it should be propagated`() {
        val command = UpdateSuperAdmCommand(id = EMPTY_UUID)
        every { repository.findById(command.id) } throws RuntimeException()

        assertThrows<RuntimeException> { sut.handle(command) }
        verify(exactly = 1) { repository.findById(command.id) }
        verify(exactly = 0) { hasher.compare(any(), any()) }
        verify(exactly = 0) { hasher.hash(any()) }
    }

    @Test
    fun `if compare throws exception, it should be propagated`() {
        val superAdm = SuperAdminBuilder().build()
        val command =
            UpdateSuperAdmCommand(id = superAdm.id, currentPassword = "password", newPassword = "new password")
        every { repository.findById(command.id) } returns superAdm
        every { hasher.compare(password = command.currentPassword!!, superAdm.passwordHash) } throws RuntimeException()

        assertThrows<RuntimeException> { sut.handle(command) }
        verify(exactly = 1) { repository.findById(command.id) }
        verify(exactly = 1) { hasher.compare(password = command.currentPassword!!, superAdm.passwordHash) }
        verify(exactly = 0) { hasher.hash(any()) }
    }

    @Test
    fun `if hash throws exception, it should be propagated`() {
        val superAdm = SuperAdminBuilder().build()
        val command = UpdateSuperAdmCommand(id = superAdm.id, currentPassword = "password", newPassword = "new password")
        every { repository.findById(command.id) } returns superAdm
        every { hasher.compare(password = command.currentPassword!!, superAdm.passwordHash) } returns true
        every { hasher.hash(command.newPassword!!) } throws RuntimeException()

        assertThrows<RuntimeException> { sut.handle(command) }
        verify(exactly = 1) { repository.findById(command.id) }
        verify(exactly = 1) { hasher.compare(password = command.currentPassword!!, superAdm.passwordHash) }
        verify(exactly = 1) { hasher.hash(command.newPassword!!) }
    }

    @Test
    fun `if trying to change password without passing current password, throws exception`() {
        val superAdm = SuperAdminBuilder().build()
        val command = UpdateSuperAdmCommand(id = superAdm.id, newPassword = "new password")
        every { repository.findById(command.id) } returns superAdm

        assertThrows<BadRequestException.MissingCurrentPasswordException> { sut.handle(command) }
        verify(exactly = 1) { repository.findById(command.id) }
        verify(exactly = 0) { hasher.compare(any(), any()) }
        verify(exactly = 0) { hasher.hash(any()) }
    }

    @Test
    fun `if trying to change password but current password is incorrect, throws exception`() {
        val superAdm = SuperAdminBuilder().build()
        val command = UpdateSuperAdmCommand(
            id = superAdm.id,
            currentPassword = "incorrect password",
            newPassword = "new password"
        )
        every { repository.findById(command.id) } returns superAdm
        every { hasher.compare(password = command.currentPassword!!, hashed = superAdm.passwordHash) } returns false

        assertThrows<UnauthorizedException.IncorrectPasswordException> { sut.handle(command) }
        verify(exactly = 1) { repository.findById(command.id) }
        verify(exactly = 1) { hasher.compare(password = command.currentPassword!!, superAdm.passwordHash) }
        verify(exactly = 0) { hasher.hash(any()) }
    }
}