package com.interdisciplinar2025.remake.domain

import com.interdisciplinar2025.remake.common.builders.PaymentBuilder
import com.interdisciplinar2025.remake.domain.model.Payment
import com.interdisciplinar2025.remake.domain.model.Payment.PaymentStatus
import com.interdisciplinar2025.remake.domain.shared.EMPTY_UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class PaymentTests {
    private lateinit var sut: Payment

    @BeforeEach
    fun setUp() {
        sut = PaymentBuilder.builder().build()
    }

    @Test
    fun `when Value is negative, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Payment.create(
                value = -1.0,
                status = sut.status,
                dueDate = sut.dueDate,
                paymentDate = sut.paymentDate,
                method = sut.method,
                payerFactoryId = sut.payerFactoryId,
                paidPlanId = sut.paidPlanId
            )
        }
    }

    @Test
    fun `when Status is PENDING but Payment Date is in the past, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Payment.create(
                value = sut.value,
                status = PaymentStatus.PENDING,
                dueDate = sut.dueDate,
                paymentDate = LocalDateTime.now().minusDays(2),
                method = sut.method,
                payerFactoryId = sut.payerFactoryId,
                paidPlanId = sut.paidPlanId
            )
        }
    }

    @Test
    fun `when Status is PAID but Payment Date is null, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Payment.create(
                value = sut.value,
                status = PaymentStatus.PAID,
                dueDate = sut.dueDate,
                paymentDate = null,
                method = sut.method,
                payerFactoryId = sut.payerFactoryId,
                paidPlanId = sut.paidPlanId
            )
        }
    }

    @Test
    fun `when Payer Factory Id is empty, fails do create object`() {
        assertThrows<IllegalArgumentException> {
            Payment.create(
                value = sut.value,
                status = sut.status,
                dueDate = sut.dueDate,
                paymentDate = sut.paymentDate,
                method = sut.method,
                payerFactoryId = EMPTY_UUID,
                paidPlanId = sut.paidPlanId
            )
        }
    }

    @Test
    fun `when Paid Plan Id is empty, fails do create object`() {
        assertThrows<IllegalArgumentException> {
            Payment.create(
                value = sut.value,
                status = sut.status,
                dueDate = sut.dueDate,
                paymentDate = sut.paymentDate,
                method = sut.method,
                payerFactoryId = sut.payerFactoryId,
                paidPlanId = EMPTY_UUID
            )
        }
    }

    @Test
    fun `when Status is PENDING and Payment Date is in the future, creates object`() {
        assertDoesNotThrow {
            Payment.create(
                value = sut.value,
                status = sut.status,
                dueDate = sut.dueDate,
                paymentDate = LocalDateTime.now().plusMonths(1),
                method = sut.method,
                payerFactoryId = sut.payerFactoryId,
                paidPlanId = sut.paidPlanId
            )
        }
    }

    @Test
    fun `when Status is PAID but Payment date is in the future, fails to create object`() {
        assertThrows<IllegalArgumentException> {
            Payment.create(
                value = sut.value,
                status = PaymentStatus.PAID,
                dueDate = sut.dueDate,
                paymentDate = LocalDateTime.now().plusDays(1),
                method = sut.method,
                payerFactoryId = sut.payerFactoryId,
                paidPlanId = sut.paidPlanId
            )
        }
    }

    @Test
    fun `when all data is valid, then creates object`() {
        val payment = Payment.create(
            value = sut.value,
            status = sut.status,
            dueDate = sut.dueDate,
            paymentDate = sut.paymentDate,
            method = sut.method,
            payerFactoryId = sut.payerFactoryId,
            paidPlanId = sut.paidPlanId
        )

        assert(payment.value == sut.value)
        assert(payment.status == sut.status)
        assert(payment.dueDate == sut.dueDate)
        assert(payment.paymentDate == sut.paymentDate)
        assert(payment.method == sut.method)
        assert(payment.payerFactoryId == sut.payerFactoryId)
        assert(payment.paidPlanId == sut.paidPlanId)
    }
}