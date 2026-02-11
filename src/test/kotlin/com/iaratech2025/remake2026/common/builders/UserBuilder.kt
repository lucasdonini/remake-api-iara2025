package com.iaratech2025.remake2026.common.builders

import com.iaratech2025.remake2026.domain.model.Email
import com.iaratech2025.remake2026.domain.model.User
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class UserBuilder(private val clock: Clock) {
    private var manager: User? = null
    private var name: String = "Marcelo Grilo"
    private var gender: User.Gender? = User.Gender.MALE
    private var birthday: LocalDate = LocalDate.parse("1990-12-25") // Not his real birthday
    private var email: Email = Email.create("marcelo.grilo@email.com") // Not his real email
    private var passwordHash: String = "Maria Grilo"
    private var role: String = "Coordenador de Projetos"
    private var permissions: List<User.Permission> = mutableListOf(User.Permission.READ)
    private var createdAt: LocalDateTime = LocalDateTime.now(clock)
    private var isActive: Boolean = true
    private var employerFactoryId: UUID = UUID.randomUUID()

    fun build(): User = User.create(
        manager = manager,
        name = name,
        gender = gender,
        birthday = birthday,
        email = email,
        passwordHash = passwordHash,
        role = role,
        permissions = permissions,
        createdAt = createdAt,
        isActive = isActive,
        employerFactoryId = employerFactoryId,
        clock = clock
    )

    fun withName(value: String) = apply { name = value }
    fun withManager(value: User?) = apply { manager = value }
    fun withGender(value: User.Gender?) = apply { gender = value }
    fun withBirthday(value: LocalDate) = apply { birthday = value }
    fun withEmail(value: Email) = apply { email = value }
    fun withPasswordHash(value: String) = apply { passwordHash = value }
    fun withRole(value: String) = apply { role = value }
    fun withCreatedAt(value: LocalDateTime) = apply { createdAt = value }
    fun withIsActive(value: Boolean) = apply { isActive = value }
    fun withEmployerFactoryId(value: UUID) = apply { employerFactoryId = value }
    fun withPermissions(value: List<User.Permission>) = apply { permissions = value }
}