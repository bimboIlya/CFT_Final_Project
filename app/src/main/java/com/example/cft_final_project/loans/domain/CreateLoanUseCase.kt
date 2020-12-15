package com.example.cft_final_project.loans.domain

import com.example.cft_final_project.common.domain.CoroutineUseCase
import com.example.cft_final_project.loans.data.model.Loan
import com.example.cft_final_project.loans.data.sources.LoanRepository
import com.example.cft_final_project.loans.data.sources.network.LoanRequestParams

class CreateLoanUseCase(
    private val loanRepository: LoanRepository
) : CoroutineUseCase<LoanRequestParams, Loan>() {
    override suspend fun invoke(parameters: LoanRequestParams) =
        loanRepository.createLoan(parameters)
}