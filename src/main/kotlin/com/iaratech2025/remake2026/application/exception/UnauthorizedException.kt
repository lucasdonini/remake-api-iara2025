package com.iaratech2025.remake2026.application.exception

sealed class UnauthorizedException(message: String) : Exception(message) {
  class IncorrectPasswordException : UnauthorizedException("Incorrect Password")
}