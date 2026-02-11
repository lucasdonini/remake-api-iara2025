package com.interdisciplinar2025.remake.application.port

interface PasswordHasher {
    fun hash(password: String): String
    fun compare(password: String, hashed: String): Boolean
}