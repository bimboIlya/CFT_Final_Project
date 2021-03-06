package com.example.cft_final_project.loans.domain

import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.common.domain.CoroutineUseCase
import com.example.cft_final_project.loans.data.sources.LoanRepository
import kotlinx.coroutines.CoroutineDispatcher

class ClearCachedLoansUseCase(
    private val loanRepository: LoanRepository,
    private val authManager: AuthManager,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, Unit>(coroutineDispatcher) {

    override suspend fun execute(params: Unit) {
        loanRepository.clearCachedLoans()
        authManager.invalidateToken()
    }
}