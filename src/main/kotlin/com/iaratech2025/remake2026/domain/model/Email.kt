package com.iaratech2025.remake2026.domain.model

import java.util.regex.Pattern

const val EMAIL_VALIDATION_REGEX: String = "^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+"

class Email private constructor(val value: String) {
    init {
        require(value.isNotBlank()) { "The email cannot be blank" }

        val pattern = Pattern.compile(EMAIL_VALIDATION_REGEX)
        val matcher = pattern.matcher(value)
        require(matcher.matches()) { "Invalid email format" }
    }

    companion object {
        fun create(value: String): Email = Email(value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Email) return false
        return value == other.value
    }
}