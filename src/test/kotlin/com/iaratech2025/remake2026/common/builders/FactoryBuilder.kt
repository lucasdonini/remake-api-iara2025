package com.iaratech2025.remake2026.common.builders

import com.interdisciplinar2025.remake.domain.model.Address
import com.interdisciplinar2025.remake.domain.model.CEP
import com.interdisciplinar2025.remake.domain.model.CNPJ
import com.interdisciplinar2025.remake.domain.model.Email
import com.interdisciplinar2025.remake.domain.model.Factory
import com.interdisciplinar2025.remake.domain.model.Plan

class FactoryBuilder {
    private var cnpj: CNPJ = CNPJ.create("02.914.460/0192-50")
    private var isActive: Boolean = true
    private var email: Email = Email.create("contador@seara.com.br")
    private var enterpriseName: String = "Seara Alimentos Ltda"
    private var industrySector: String = "Alimentos"
    private var hiredPlan: Plan? = null
    private var address: Address = AddressBuilder()
        .withStreet("Rodovia João Beira")
        .withNumber(0u)
        .withCity("Amparo")
        .withState("SP")
        .withCep(CEP.create("13904450"))
        .build()

    fun withCNPJ(cnpj: CNPJ) = apply { this.cnpj = cnpj }
    fun withIsActive(isActive: Boolean) = apply { this.isActive = isActive }
    fun withEmail(email: Email) = apply { this.email = email }
    fun withEnterpriseName(enterpriseName: String) = apply { this.enterpriseName = enterpriseName }
    fun withIndustrySector(industrySector: String) = apply { this.industrySector = industrySector }
    fun withHiredPlan(hiredPlan: Plan?) = apply { this.hiredPlan = hiredPlan }
    fun withAddress(address: Address) = apply { this.address = address }

    fun build(): Factory = Factory.create(
        cnpj = cnpj,
        isActive = isActive,
        email = email,
        enterpriseName = enterpriseName,
        industrySector = industrySector,
        hiredPlan = hiredPlan,
        address = address
    )
}