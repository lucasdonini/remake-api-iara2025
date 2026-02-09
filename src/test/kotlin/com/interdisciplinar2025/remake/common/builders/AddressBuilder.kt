package com.interdisciplinar2025.remake.common.builders

import com.interdisciplinar2025.remake.domain.Address
import com.interdisciplinar2025.remake.domain.CEP

class AddressBuilder {
    private var state: String = "São Paulo"
    private var city: String = "São Paulo"
    private var street: String = "Rua Irineu José Bordon"
    private var number: UInt = 338u
    private var complement: String? = null
    private var cep: CEP = CEP.create("05120060")

    fun build(): Address = Address.create(
        state = state,
        city = city,
        street = street,
        number = number,
        complement = complement,
        cep = cep
    )

    fun withState(value: String) = apply { state = value }
    fun withCity(value: String) = apply { city = value }
    fun withStreet(value: String) = apply { street = value }
    fun withNumber(value: UInt) = apply { number = value }
    fun withComplement(value: String?) = apply { complement = value }
    fun withCep(value: CEP) = apply { cep = value }

    companion object {
        fun builder() = AddressBuilder()
    }
}