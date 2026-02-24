package com.iaratech2025.remake2026.domain.filter

import java.time.Period

data class PlanFilter(
    val name: String? = null,
    val value: Double? = null,
    val duration: Period? = null
)