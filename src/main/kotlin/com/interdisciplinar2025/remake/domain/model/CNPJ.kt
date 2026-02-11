package com.interdisciplinar2025.remake.domain.model

import java.util.regex.Pattern

const val CNPJ_VALIDATION_REGEX = """^\d{2}(\.\d{3}){2}/\d{4}-\d{2}$"""

class CNPJ private constructor(val value: String) {
    init {
        require(value.isNotBlank()) { "CNPJ cannot be blank" }
        require(isValid(value)) { "Invalid CNPJ format" }
    }

    companion object {
        fun create(cnpj: String): CNPJ = CNPJ(cnpj)
    }

    private fun isValid(cnpj: String): Boolean {
        val pattern = Pattern.compile(CNPJ_VALIDATION_REGEX)
        val matcher = pattern.matcher(cnpj)
        return matcher.matches()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CNPJ) return false
        return value == other.value
    }
}