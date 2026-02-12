package com.iaratech2025.remake2026.domain.model

private const val INVALID_ENTERPRISE_NAME_MESSAGE = "Enterprise name cannot be blank"
private const val INVALID_INDUSTRY_SECTOR_MESSAGE = "Industry sector cannot be blank"

class Factory private constructor(
    val cnpj: CNPJ,
    var isActive: Boolean,
    var email: Email,
    enterpriseName: String,
    industrySector: String,
    var hiredPlan: Plan?,
    val address: Address
) : AggregateRoot() {
    init {
        require(enterpriseName.isNotBlank()) { INVALID_ENTERPRISE_NAME_MESSAGE }
        require(industrySector.isNotBlank()) { INVALID_INDUSTRY_SECTOR_MESSAGE }
    }

    var enterpriseName: String = enterpriseName
        set(value) {
            require(value.isNotBlank()) { INVALID_ENTERPRISE_NAME_MESSAGE }
            field = value
        }

    var industrySector: String = industrySector
        set(value) {
            require(value.isNotBlank()) { INVALID_INDUSTRY_SECTOR_MESSAGE }
            field = value
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