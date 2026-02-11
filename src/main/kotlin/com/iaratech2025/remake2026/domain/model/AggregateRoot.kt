package com.iaratech2025.remake2026.domain.model

import com.iaratech2025.remake2026.domain.shared.EMPTY_UUID
import java.util.*

abstract class AggregateRoot {
    val id: UUID = EMPTY_UUID

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AggregateRoot
        return id != EMPTY_UUID && id == other.id
    }

    final override fun hashCode(): Int = id.hashCode()
}