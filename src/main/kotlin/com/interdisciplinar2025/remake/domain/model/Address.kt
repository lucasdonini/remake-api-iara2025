package com.interdisciplinar2025.remake.domain.model

class Address private constructor(
    val state: String,
    val city: String,
    val street: String,
    val number: UInt,
    val complement: String?,
    val cep: CEP
) : AggregateRoot() {
    init {
        require(state.isNotBlank()) { "Address' State cannot be blank" }
        require(city.isNotBlank()) { "Address' City cannot be blank" }
        require(street.isNotBlank()) { "Address' Street cannot be blank" }
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