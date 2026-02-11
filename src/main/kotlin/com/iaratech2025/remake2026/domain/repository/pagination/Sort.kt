package com.iaratech2025.remake2026.domain.repository.pagination

import com.iaratech2025.remake2026.domain.model.AggregateRoot
import com.iaratech2025.remake2026.domain.model.SortableField

data class Sort<T>(val field: SortableField<T>, val direction: Direction = Direction.ASC) where T : AggregateRoot {
    enum class Direction { ASC, DESC }
}
