package com.example.cft_final_project.loans.data.domain

import com.example.cft_final_project.common.domain.CoroutineUseCase

class ClearCachedLoansUseCase(
    private val loanRepository: LoanRepository
) : CoroutineUseCase<Unit, Unit>() {
    override suspend fun invoke(parameters: Unit) = loanRepository.clearCachedLoans()
}