package com.iaratech2025.remake2026.application.exception

sealed class ApplicationUnauthorizedException(message: String) : Exception(message) {
  class IncorrectPasswordException : ApplicationUnauthorizedException("Incorrect Password")
}