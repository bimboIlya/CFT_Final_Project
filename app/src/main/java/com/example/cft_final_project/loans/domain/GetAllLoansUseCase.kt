package com.example.cft_final_project.loans.domain

import com.example.cft_final_project.common.domain.CoroutineUseCase
import com.example.cft_final_project.loans.data.model.Loan
import com.example.cft_final_project.loans.data.sources.LoanRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetAllLoansUseCase(
    private val loanRepository: LoanRepository,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<Loan>>(coroutineDispatcher) {
    override suspend fun execute(params: Unit) = loanRepository.getAllLoans()
}