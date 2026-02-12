package com.iaratech2025.remake2026.domain.model

import com.iaratech2025.remake2026.domain.shared.EMPTY_UUID
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

private const val INVALID_NAME_MESSAGE = "User's name cannot be blank"
private const val INVALID_PASSWORD_MESSAGE = "An User must have a password"
private const val INVALID_ROLE_MESSAGE = "User's Role cannot be blank"
private const val INVALID_CREATION_DATE_MESSAGE = "An User's creation date cannot be in the future"
private const val INVALID_EMPLOYER_FACTORY_ID_MESSAGE = "User's employer Factory Id must not be empty"
private const val INVALID_BIRTHDAY_MESSAGE = "User must be at least 16 years old"

class User private constructor(
    var manager: User?,
    name: String,
    var gender: Gender?,
    val birthday: LocalDate,
    var email: Email,
    passwordHash: String,
    role: String,
    var permissions: List<Permission>,
    val createdAt: LocalDateTime,
    var isActive: Boolean,
    employerFactoryId: UUID,
    clock: Clock
) : AggregateRoot() {
    init {
        require(name.isNotBlank()) { INVALID_NAME_MESSAGE }
        require(passwordHash.isNotBlank()) { INVALID_PASSWORD_MESSAGE }
        require(role.isNotBlank()) { INVALID_ROLE_MESSAGE }
        require(!createdAt.isAfter(LocalDateTime.now(clock))) { INVALID_CREATION_DATE_MESSAGE }
        require(employerFactoryId != EMPTY_UUID) { INVALID_EMPLOYER_FACTORY_ID_MESSAGE }
        require(!birthday.isAfter(LocalDate.now(clock).minusYears(16))) { INVALID_BIRTHDAY_MESSAGE }
    }

    var name: String = name
        set(value) {
            require(value.isNotBlank()) { INVALID_NAME_MESSAGE }
            field = value
        }

    var passwordHash: String = passwordHash
        set(value) {
            require(value.isNotBlank()) { INVALID_PASSWORD_MESSAGE }
            field = value
        }

    var role: String = role
        set(value) {
            require(value.isNotBlank()) { INVALID_ROLE_MESSAGE }
            field = value
        }

    var employerFactoryId: UUID = employerFactoryId
        set(value) {
            require(value != EMPTY_UUID) { INVALID_EMPLOYER_FACTORY_ID_MESSAGE }
            field = value
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