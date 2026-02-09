package com.interdisciplinar2025.remake.domain.model

import java.time.Period

class Plan private constructor(
    val name: String,
    val price: Double,
    val description: String?,
    val duration: Period
) : AggregateRoot() {
    init {
        require(name.isNotBlank()) { "Plan's name cannot be blank" }
        require(price >= 0) { "Plan's price cannot be negative" }
        require(duration.years > 0 || duration.months > 0) { "Plan's duration must be of at least one month" }
    }

    companion object {
        fun create(
            name: String,
            price: Double,
            description: String?,
            duration: Period
        ): Plan = Plan(
            name = name,
            price = price,
            description = description,
            duration = duration
        )
    }
}