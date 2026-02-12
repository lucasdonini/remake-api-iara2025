package com.iaratech2025.remake2026.domain.model

private const val INVALID_STATE_MESSAGE = "Address' State cannot be blank"
private const val INVALID_CITY_MESSAGE = "Address' City cannot be blank"
private const val INVALID_STREET_MESSAGE = "Address' Street cannot be blank"

class Address private constructor(
    state: String,
    city: String,
    street: String,
    var number: UInt,
    var complement: String?,
    var cep: CEP
) : AggregateRoot() {
    init {
        require(state.isNotBlank()) { INVALID_STATE_MESSAGE }
        require(city.isNotBlank()) { INVALID_CITY_MESSAGE }
        require(street.isNotBlank()) { INVALID_STREET_MESSAGE }
    }

    var state: String = state
        set(value) {
            require(value.isNotBlank()) { INVALID_STATE_MESSAGE }
            field = value
        }

    var city: String = city
        set(value) {
            require(value.isNotBlank()) { INVALID_CITY_MESSAGE }
            field = value
        }

    var street: String = street
        set(value) {
            require(value.isNotBlank()) { INVALID_STREET_MESSAGE }
            field = value
        }

    companion object {
        fun create(
            state: String,
            city: String,
            street: String,
            number: UInt,
            complement: String?,
            cep: CEP
        ): Address = Address(
            state = state,
            city = city,
            street = street,
            number = number,
            complement = complement,
            cep = cep
        )
    }
}