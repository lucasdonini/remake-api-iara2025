package com.iaratech2025.remake2026.domain.model

// TODO: implement hashCode()
class CEP private constructor(val value: String) {
    init {
        require(value.length == 8)
    }

    companion object {
        fun create(value: String): CEP = CEP(value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CEP) return false
        return value == other.value
    }
}