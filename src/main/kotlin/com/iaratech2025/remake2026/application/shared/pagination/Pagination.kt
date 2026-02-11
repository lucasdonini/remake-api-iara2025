package com.iaratech2025.remake2026.application.shared.pagination

data class Pagination(val page: UInt, val size: UInt) {
    init {
        require(size > 0u) { "Size must be greater than 0" }
    }

    fun offset(): UInt = page * size
}
