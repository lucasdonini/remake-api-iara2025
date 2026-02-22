package com.iaratech2025.remake2026.application.usecase.superadm.delete

import com.iaratech2025.remake2026.domain.repository.SuperAdmRepository
import org.springframework.stereotype.Component

@Component
class DeleteSuperAdmHandler(val repository: SuperAdmRepository) {
    fun handle(command: DeleteSuperAdmCommand) = repository.deleteById(command.id)
}