package com.iaratech2025.remake2026.domain.model

class Factory private constructor(
    val cnpj: CNPJ,
    val isActive: Boolean,
    val email: Email,
    val enterpriseName: String,
    val industrySector: String,
    val hiredPlan: Plan?,
    val address: Address
) : AggregateRoot() {
    init {
        require(enterpriseName.isNotBlank()) { "Enterprise name cannot be blank" }
        require(industrySector.isNotBlank()) { "Industry sector cannot be blank" }
    }

    companion object {
        fun create(
            cnpj: CNPJ,
            isActive: Boolean,
            email: Email,
            enterpriseName: String,
            industrySector: String,
            hiredPlan: Plan?,
            address: Address
        ): Factory = Factory(
            cnpj = cnpj,
            isActive = isActive,
            email = email,
            enterpriseName = enterpriseName,
            industrySector = industrySector,
            hiredPlan = hiredPlan,
            address = address
        )
    }
}