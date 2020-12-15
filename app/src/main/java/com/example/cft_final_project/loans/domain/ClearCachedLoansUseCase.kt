package com.example.cft_final_project.loans.domain

import com.example.cft_final_project.common.domain.CoroutineUseCase
import com.example.cft_final_project.loans.data.sources.LoanRepository

class ClearCachedLoansUseCase(
    private val loanRepository: LoanRepository
) : CoroutineUseCase<Unit, Unit>() {
    override suspend fun invoke(parameters: Unit) = loanRepository.clearCachedLoans()
}