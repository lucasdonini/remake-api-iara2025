package com.iaratech2025.remake2026.common.builders

import com.iaratech2025.remake2026.domain.model.Plan
import java.time.Period

class PlanBuilder private constructor() {
    private var name: String = "plan"
    private var price: Double = 10.0
    private var description: String? = null
    private var duration: Period = Period.ofMonths(1)

    fun build(): Plan = Plan.create(
        name = name,
        price = price,
        description = description,
        duration = duration
    )

    companion object {
        fun builder(): PlanBuilder = PlanBuilder()
    }
}