package com.interdisciplinar2025.remake.application.shared.pagination

data class PageResult<T>(
    val content: List<T>,
    val totalElements: ULong,
    val page: UInt,
    val size: UInt
) {
    init {
        require(size > 0u) { "Size must be greater than 0" }
    }
}
