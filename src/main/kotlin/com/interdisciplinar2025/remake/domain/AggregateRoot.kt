package com.interdisciplinar2025.remake.domain

import java.util.UUID

abstract class AggregateRoot {
    val id: UUID = UUID(0L, 0L) // Empty id. The database is responsible for generating it
}