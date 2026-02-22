package com.iaratech2025.remake2026.application.usecase.superadm.delete

import com.iaratech2025.remake2026.domain.repository.SuperAdmRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.util.UUID
import kotlin.test.Test

class DeleteSuperAdmUseCaseTests {
    private val repository: SuperAdmRepository = mockk()
    private lateinit var sut: DeleteSuperAdmHandler

    @BeforeEach
    fun setUp() {
        sut = DeleteSuperAdmHandler(repository)
    }

    @Test
    fun `should delete super admin by id`() {
        val command = DeleteSuperAdmCommand(UUID.randomUUID())
        every { repository.deleteById(command.id) } returns Unit

        sut.handle(command)

        verify(exactly = 1) { repository.deleteById(command.id) }
    }

    @Test
    fun `if exception is thrown, it should propagate`() {
        val command = DeleteSuperAdmCommand(java.util.UUID.randomUUID())
        every { repository.deleteById(command.id) } throws RuntimeException("Database error")

        assertThrows<RuntimeException> { sut.handle(command) }

        verify(exactly = 1) { repository.deleteById(command.id) }
    }
}