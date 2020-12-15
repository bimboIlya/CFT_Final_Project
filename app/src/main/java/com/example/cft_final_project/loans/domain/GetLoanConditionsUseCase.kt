package com.example.cft_final_project.loans.domain

import com.example.cft_final_project.common.domain.CoroutineUseCase
import com.example.cft_final_project.loans.data.model.LoanConditions
import com.example.cft_final_project.loans.data.sources.LoanRepository

class GetLoanConditionsUseCase(
    private val loanRepository: LoanRepository
) : CoroutineUseCase<Unit, LoanConditions>() {
    override suspend fun invoke(parameters: Unit) = loanRepository.getLoanConditions()
}