package com.example.cft_final_project.loans.data.domain

import com.example.cft_final_project.common.domain.CoroutineUseCase
import com.example.cft_final_project.loans.data.model.LoanConditions

class GetLoanConditionsUseCase(
    private val loanRepository: LoanRepository
) : CoroutineUseCase<Unit, LoanConditions>() {
    override suspend fun invoke(parameters: Unit) = loanRepository.getLoanConditions()
}