package com.example.cft_final_project.loans.data

import com.example.cft_final_project.common.network.Result
import com.example.cft_final_project.loans.data.model.Loan
import com.example.cft_final_project.loans.data.model.LoanConditions
import com.example.cft_final_project.loans.data.network.LoanRequestParams

interface LoanRepository {

    suspend fun createLoan(loanParams: LoanRequestParams): Result<Loan>

    suspend fun getLoanById(id: Int): Result<Loan>

    suspend fun getAllLoans(): Result<List<Loan>>

    suspend fun getLoanConditions(): Result<LoanConditions>
}