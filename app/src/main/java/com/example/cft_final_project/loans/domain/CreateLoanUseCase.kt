package com.example.cft_final_project.loans.domain

import com.example.cft_final_project.common.domain.CoroutineUseCase
import com.example.cft_final_project.loans.data.model.Loan
import com.example.cft_final_project.loans.data.sources.LoanRepository
import com.example.cft_final_project.loans.data.sources.network.LoanRequestParams
import kotlinx.coroutines.CoroutineDispatcher

class CreateLoanUseCase(
    private val loanRepository: LoanRepository,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<LoanRequestParams, Loan>(coroutineDispatcher) {
    override suspend fun execute(params: LoanRequestParams) =
        loanRepository.createLoan(params)
}