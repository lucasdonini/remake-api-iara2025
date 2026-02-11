package com.iaratech2025.remake2026.domain.repository.pagination

import com.iaratech2025.remake2026.domain.model.AggregateRoot

data class Pagination<T>(val page: UInt, val size: UInt, val sort: Sort<T>) where T : AggregateRoot {
    init {
        require(size > 0u) { "Size must be greater than 0" }
    }

    fun offset(): UInt = page * size
}
