package com.iaratech2025.remake2026.application.port

interface PasswordHasher {
    fun hash(password: String): String
    fun compare(password: String, hashed: String): Boolean
}