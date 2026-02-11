package com.iaratech2025.remake2026.domain.model

import com.iaratech2025.remake2026.domain.shared.EMPTY_UUID
import java.time.LocalDateTime
import java.util.*

class Payment private constructor(
    val value: Double,
    val status: PaymentStatus,
    val dueDate: LocalDateTime,
    val paymentDate: LocalDateTime?,
    val method: PaymentMethod,
    val payerFactoryId: UUID,
    val paidPlanId: UUID
) : AggregateRoot() {
    init {
        require(value >= 0) { "Payment value cannot be negative" }
        require(
            isValidState(
                status,
                paymentDate
            )
        ) { "Invalid Payment state: $status payments must ${if (status == PaymentStatus.PENDING) "not have" else "have"} a past payment date" }
        require(payerFactoryId != EMPTY_UUID) { "Payer Factory's Id must no be empty" }
        require(paidPlanId != EMPTY_UUID) { "Paid Plan's Id must no be empty" }
    }

    private fun isValidState(status: PaymentStatus, paymentDate: LocalDateTime?): Boolean {
        val now = LocalDateTime.now()
        return when (status) {
            PaymentStatus.PAID -> paymentDate != null && paymentDate.isBefore(now)
            PaymentStatus.PENDING -> paymentDate == null || paymentDate.isAfter(now)
        }
    }

    companion object {
        fun create(
            value: Double,
            status: PaymentStatus,
            dueDate: LocalDateTime,
            paymentDate: LocalDateTime?,
            method: PaymentMethod,
            payerFactoryId: UUID,
            paidPlanId: UUID
        ): Payment = Payment(
            value = value,
            status = status,
            dueDate = dueDate,
            paymentDate = paymentDate,
            method = method,
            payerFactoryId = payerFactoryId,
            paidPlanId = paidPlanId
        )
    }


    enum class PaymentStatus {
        PAID,
        PENDING
    }

    enum class PaymentMethod {
        CREDIT,
        DEBIT,
        CHECK,
        PIX
    }
}