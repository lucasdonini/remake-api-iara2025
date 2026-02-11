package com.iaratech2025.remake2026.common.builders

import com.iaratech2025.remake2026.domain.model.Payment
import com.iaratech2025.remake2026.domain.model.Payment.PaymentMethod
import com.iaratech2025.remake2026.domain.model.Payment.PaymentStatus
import java.time.LocalDateTime
import java.util.*

class PaymentBuilder private constructor() {
    private var value: Double = 10.0
    private var status: PaymentStatus = PaymentStatus.PENDING
    private var dueDate: LocalDateTime = LocalDateTime.now().plusMonths(1)
    private var paymentDate: LocalDateTime? = null
    private var method: PaymentMethod = PaymentMethod.PIX
    private var payerFactoryId: UUID = UUID.randomUUID()
    private var paidPlanId: UUID = UUID.randomUUID()

    fun withValue(value: Double) = apply { this.value = value }
    fun withStatus(value: PaymentStatus) = apply { status = value }
    fun withDueDate(value: LocalDateTime) = apply { dueDate = value }
    fun withPaymentDate(value: LocalDateTime?) = apply { paymentDate = value }
    fun withMethod(value: PaymentMethod) = apply { method = value }
    fun withPayerFactoryId(value: UUID) = apply { payerFactoryId = value }
    fun withPaidPlanId(value: UUID) = apply { paidPlanId = value }

    fun build(): Payment = Payment.create(
        value = value,
        status = status,
        dueDate = dueDate,
        paymentDate = paymentDate,
        method = method,
        payerFactoryId = payerFactoryId,
        paidPlanId = paidPlanId
    )

    companion object {
        fun builder(): PaymentBuilder = PaymentBuilder()
    }
}