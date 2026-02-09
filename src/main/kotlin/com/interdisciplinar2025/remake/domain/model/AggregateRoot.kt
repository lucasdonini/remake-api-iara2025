package com.interdisciplinar2025.remake.domain.model

import com.interdisciplinar2025.remake.domain.shared.EMPTY_UUID
import java.util.UUID

abstract class AggregateRoot {
    val id: UUID = EMPTY_UUID
}