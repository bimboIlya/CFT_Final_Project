package com.example.cft_final_project.loans.data.sources

import com.example.cft_final_project.loans.data.model.Loan
import com.example.cft_final_project.loans.data.model.LoanConditions
import com.example.cft_final_project.loans.data.sources.network.LoanRequestParams

interface LoanRepository {

    suspend fun createLoan(loanParams: LoanRequestParams): Loan

    suspend fun getLoanById(id: Int): Loan

    suspend fun getAllLoans(): List<Loan>

    suspend fun getLoanConditions(): LoanConditions

    suspend fun clearCachedLoans()
}