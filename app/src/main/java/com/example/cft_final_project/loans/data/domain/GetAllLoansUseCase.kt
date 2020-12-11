package com.example.cft_final_project.loans.data.domain

import com.example.cft_final_project.common.domain.CoroutineUseCase
import com.example.cft_final_project.loans.data.model.Loan

class GetAllLoansUseCase(
    private val loanRepository: LoanRepository
) : CoroutineUseCase<Unit, List<Loan>>() {
    override suspend fun invoke(parameters: Unit) = loanRepository.getAllLoans()
}