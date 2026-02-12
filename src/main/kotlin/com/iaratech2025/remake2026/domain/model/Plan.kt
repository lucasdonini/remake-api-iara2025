package com.iaratech2025.remake2026.domain.model

import java.time.Period

private const val INVALID_PLAN_NAME_MESSAGE = "Plan's name cannot be blank"
private const val INVALID_PLAN_PRICE_MESSAGE = "Plan's price cannot be negative"
private const val INVALID_PLAN_DURATION_MESSAGE = "Plan's duration must be of at least one month"

class Plan private constructor(
    name: String,
    price: Double,
    var description: String?,
    duration: Period
) : AggregateRoot() {
    init {
        require(name.isNotBlank()) { INVALID_PLAN_NAME_MESSAGE }
        require(price >= 0) { INVALID_PLAN_PRICE_MESSAGE }
        require(duration.years > 0 || duration.months > 0) { INVALID_PLAN_DURATION_MESSAGE }
    }

    var name: String = name
        set(value) {
            require(value.isNotBlank()) { INVALID_PLAN_NAME_MESSAGE }
            field = value
        }

    var price: Double = price
        set(value) {
            require(value >= 0) { INVALID_PLAN_PRICE_MESSAGE }
            field = value
        }

    var duration: Period = duration
        set(value) {
            require(value.years > 0 || value.months > 0) { INVALID_PLAN_DURATION_MESSAGE }
            field = value
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