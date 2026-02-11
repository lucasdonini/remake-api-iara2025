package com.iaratech2025.remake2026.domain.model

import com.iaratech2025.remake2026.domain.shared.EMPTY_UUID
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class User private constructor(
    val manager: User?,
    val name: String,
    val gender: Gender?,
    val birthday: LocalDate,
    val email: Email,
    val passwordHash: String,
    val role: String,
    val permissions: List<Permission>,
    val createdAt: LocalDateTime,
    val isActive: Boolean,
    val employerFactoryId: UUID,
    clock: Clock
) : AggregateRoot() {
    init {
        require(name.isNotBlank()) { "User's name cannot be blank" }
        require(passwordHash.isNotBlank()) { "An User must have a password" }
        require(role.isNotBlank()) { "User's Role cannot be blank" }
        require(!createdAt.isAfter(LocalDateTime.now(clock))) { "An User's creation date cannot be in the future" }
        require(employerFactoryId != EMPTY_UUID) { "User's employer Factory Id must not be empty" }
        require(!birthday.isAfter(LocalDate.now(clock).minusYears(16))) { "User must be at least 16 years old" }
    }

    companion object {
        fun create(
            manager: User?,
            name: String,
            gender: Gender?,
            birthday: LocalDate,
            email: Email,
            passwordHash: String,
            role: String,
            permissions: List<Permission>,
            createdAt: LocalDateTime,
            isActive: Boolean,
            employerFactoryId: UUID,
            clock: Clock
        ): User = User(
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
    }

    enum class Gender { MALE, FEMALE, OTHER }

    enum class Permission {
        READ,
        TAKE_PHOTO,
        APROVE_PHOTO,
        REGISTER_ABACUS,
        REGISTER_USER
    }

    enum class UserSortableField : SortableField<User> {
        MANAGER,
        GENDER,
        ROLE,
        PERMISSIONS,
        EMPLOYER_FACTORY_ID
    }
}