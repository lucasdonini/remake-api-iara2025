package com.interdisciplinar2025.remake.domain

class CEP private constructor(val value: String) {
    init {
        require(value.length == 8)
    }

    companion object {
        fun create(value: String): CEP = CEP(value)
    }
}